<template>
  <section>
    <div class="main_container">
      <div class="sub_container">
        <span class="label">커버 게시글 카테고리: </span>
        <b-form-select class="category_select" v-model="articleCategoryId" :options="options">
          <template #first>
            <b-form-select-option :value="null">카테고리 없음</b-form-select-option>
          </template>
        </b-form-select>
      </div>
      <div class="sub_container">
        <span class="label">커버 카테고리: </span>
        <b-form-select class="category_select" v-model="coverCategoryId" :options="options">
          <template #first>
            <b-form-select-option :value="null">카테고리 없음</b-form-select-option>
          </template>
        </b-form-select>
      </div>
      <div class="btn_container">
        <b-button class="button" variant="primary" @click="change">변경</b-button>
        <b-button class="button" variant="danger" @click="cancel">취소</b-button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'ManageCover',
  async created() {
    await this.$store.dispatch('GET_COVER_CATEGORY_ID');
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');

    this.articleCategoryId = this.cover.articleCategoryId;
    this.coverCategoryId = this.cover.categoryId;
    this.options = this.getOptions();
  },
  computed: {
    cover() {
      return this.$store.state.coverCategoryId;
    }
  },
  data() {
    return {
      articleCategoryId: null,
      coverCategoryId: null,
      options: [],
    }
  },
  methods: {
    async change() {
      try {
        await this.$store.dispatch('UPDATE_COVER_CATEGORY_ID', {
          articleCategoryId: this.articleCategoryId,
          categoryId: this.coverCategoryId,
        });
        await this.$router.push("/manage");
      } catch (error) {
        if (error.response.status === 401) {
          alert("블로그 관리자만 변경할 수 있습니다.");
          await this.$router.push('/login');
        } else {
          alert("예기치 못한 오류가 발생했습니다.");
        }
      }
    },
    cancel() {
      this.$router.push("/manage");
    },
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
  }
};
</script>

<style lang="scss" scoped>
section {
  display: flex;
  width: 100vw;
  height: 100vh;
  justify-content: center;
  align-items: center;
  .main_container {
    display: flex;
    flex-direction: column;
    padding: 1.875em;
    border: 1px solid;
    border-radius: 0.500em;
    .sub_container {
      padding: 0.625em;
    }
    .btn_container {
      align-self: flex-end;
      .button {
        width: 6.250em;
        margin: 0.625em;
      }
    }
  }
}

@media screen and (max-width: 660px) {
  section {
    font-size: 14px;
    .main_container {
      border: none;
      .sub_container {
        flex-direction: column;
        padding: 1em 0.625em;
      }
    }
  }
}
</style>