<template>
  <section>
    <div class="form_container">
      <b-form-select class="category_select" v-model="selected" :options="options" size="sm">
        <template #first>
          <b-form-select-option :value="null" disabled>카테고리 선택</b-form-select-option>
        </template>
      </b-form-select>
      <div class="thumbnail_box">
        <div class="thumbnail">섬네일</div>
        <Thumbnail :prevThumbnail="articleData.thumbnail"></Thumbnail>
      </div>
      <input type="text" v-model="articleData.title" placeholder="제목을 입력하세요." required>
      <VueEditor v-model="articleData.content" useCustomImageHandler
                 @image-added="handleImageAdded"
                 @image-removed="handleImageRemoved"></VueEditor>
      <div class="btn_container">
        <div class="post_btn" @click="submit">등록</div>
        <div class="cancel_btn" @click="cancel">취소</div>
      </div>
    </div>
  </section>
</template>

<script>
import { VueEditor, Quill } from "vue2-editor";
import Thumbnail from '@/components/Thumbnail';
import uuidGenerator from '@/common/uuidGenerator';

export default {
  components: {
    VueEditor,
    Thumbnail
  },
  async created() {
    if (this.$route.params.articleId) {
      await this.$store.dispatch('GET_ARTICLE_DATA', this.$route.params.articleId);
    }
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');

    if (this.$route.params.articleId) {
      this.articleData = this.$store.state.articleData;
      this.selected = this.articleData.categoryId;
    }
    this.options = this.getOptions();
  },
  computed: {
    thumbnailFile() {
      return this.$store.state.thumbnailFile;
    }
  },
  data() {
    const imageFiles = new Map();

    return {
      articleData: { title: '', content: '' },
      imageFiles,

      selected: null,
      options: [],
    }
  },
  methods: {
    getOptions() {
      const categories = this.$store.state.asideCategoryList.list;
      const options = [];
      for(let i = 0; i < categories.length; i++) {
        options.push({ value: categories[i].id, text: categories[i].category });
        if (!categories[i].children) continue;
        let children = categories[i].children;
        for (let j = 0; j < children.length; j++) {
          options.push({
            value: children[j].id,
            text: `${categories[i].category}/${children[j].category}`
          });
        }
      }
      return options;
    },
    async submit() {
      if (!this.selected) {
        alert("카테고리를 선택하세요.");
        return;
      }
      if (!this.articleData.title) {
        alert("제목을 입력하세요.");
        return;
      }
      if (!this.articleData.content) {
        alert("내용을 입력하세요.");
        return;
      }

      const isPost = confirm("게시글을 등록하시겠습니까?");
      if (!isPost) return;
      let content = this.articleData.content;

      const formData = new FormData();
      for (let [key, value] of this.imageFiles) {
        let imageName = uuidGenerator() + this.getImageType(value.name);
        content = content.replace(key, imageName);
        formData.append("imageFile", value, imageName);
      }

      if (this.thumbnailFile) {
        const thumbnail = uuidGenerator() + this.getImageType(this.thumbnailFile.name);
        formData.append("thumbnailFile", this.thumbnailFile, thumbnail);
      }
      formData.append("categoryId", this.options[this.selected - 1].value);
      formData.append("title", this.articleData.title);
      formData.append("content", content);

      try {
        await this.$store.dispatch('UPLOAD_ARTICLE_DATA', formData);
      } catch (error) {
        if (error.response.status === 401) {
          alert("블로그 관리자만 게시글을 등록할 수 있습니다.");
        } else {
          alert("게시글 등록에 실패했습니다.");
        }
      }
    },
    cancel() {
      const isCancel = confirm("글쓰기를 취소하시겠습니까?");
      if (isCancel) {
        const url = `${window.location.protocol}//${window.location.host}`;
        if (!this.$route.params.articleId) {
          window.location.href = `${url}`;
        } else {
          window.location.href = `${url}/${this.$route.params.articleId}`;
        }
      }
    },
    getImageType(imageName) {
      const regex = /(.+)(\.\w+)/;
      const result = imageName.match(regex);
      console.log(result);
      return result[2];
    },
    handleImageAdded(file, Editor, cursorLocation, resetUploader) {
      const blobUrl = URL.createObjectURL(file);

      // blob 이미지가 안올라가는 문제 해결
      const Image = Quill.import('formats/image');
      Image.sanitize = url => url;
      // blob 이미지 url 추가
      Editor.insertEmbed(cursorLocation, 'image', blobUrl);
      // 커서를 맨뒤로 이동하고 다음줄로 넘어감
      Editor.setSelection(Editor.getSelection().index + 1);
      Editor.insertText(Editor.getSelection().index, '\n');
      resetUploader();
      this.imageFiles.set(blobUrl, file);
    },
    handleImageRemoved(blobUrl) {
      this.imageFiles.delete(blobUrl);
    },
  }
};
</script>

<style lang="scss" scoped>
$button-color: #1a72e6;
$button-hover-color: #4383ee;
$cancel-hover-color: #f1f1f1;
$active-color: #a7daed;

section {
  font-family: 'Noto Sans KR', sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 48px 24px;

  .form_container {
    width: 100%;
    max-width: 880px;

    .category_select {
      width: 220px;
      align-self: flex-start;
      margin-bottom: 10px;
    }

    input {
      width: 100%;
      padding: 6px 4px 6px 8px;
      margin-bottom: 24px;
      height: 38px;
    }
    #editor {
      height: 450px;
    }
    .btn_container {
      margin-top: 24px;
      display: flex;
      justify-content: center;
      align-items: center;
      > * {
        cursor: pointer;
        font-size: 20px;
        padding: 8px 56px;
        margin: 0 16px;
        border-radius: 4px;
        border: 1px solid;
      }
      .post_btn {
        background-color: $button-color;
        color: white;
        border-color: $button-color;
        &:hover, &:active {
          background-color: $button-hover-color;
          border-color: $button-hover-color;
        }
      }
      .cancel_btn {
        &:hover {
          background-color: $cancel-hover-color;
        }
      }
    }
  }
}

.thumbnail_box {
  .thumbnail {
    font-size: 16px;
    padding: 4px 8px;
    font-weight: 400;
  }
  margin-bottom: 15px;
}

@media screen and (max-width: 768px) {
  section {
    font-size: 14px;
    padding: 36px 12px;
    .form_container {
      input {
        margin-bottom: 16px;
      }
      .btn_container {
        margin-top: 16px;
        > * {
          font-size: inherit;
          padding: 6px 0;
          margin: 0;
          flex: 1 1 50%;
          text-align: center;
        }
        .post_btn {
          margin-right: 6px;
        }
        .cancel_btn {
          margin-left: 6px;
          &:hover {
            background-color: white;
          }
          &:active {
            background-color: $active-color
          }
        }
      }
    }
  }
}
</style>