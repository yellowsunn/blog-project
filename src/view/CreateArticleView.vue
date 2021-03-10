<template>
  <section>
    <div class="form_container">
      <b-form-select class="category_select" v-model="selected" :options="options" size="sm">
        <template #first>
          <b-form-select-option :value="null" disabled>카테고리 선택</b-form-select-option>
        </template>
      </b-form-select>
      <input type="text" v-model="articleData.title" placeholder="제목을 입력하세요." required>
      <VueEditor v-model="articleData.content"></VueEditor>
      <div class="btn_container">
        <div class="post_btn">등록</div>
        <div class="cancel_btn" @click="cancel">취소</div>
      </div>
    </div>
  </section>
</template>

<script>
import { VueEditor, Quill } from "vue2-editor";

export default {
  components: {
    VueEditor
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
  data() {
    return {
      articleData: { title: '', content: '' },
      selected: null,
      options: [],
      thumbnail: null,
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
    }
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
  padding: 72px 24px;

  .form_container {
    width: 100%;
    max-width: 880px;

    .category_select {
      width: 220px;
      align-self: flex-start;
      margin-bottom: 24px;
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

@media screen and (max-width: 768px) {
  section {
    font-size: 14px;
    padding: 36px 12px;
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
</style>