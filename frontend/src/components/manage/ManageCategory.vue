<template>
  <section>
    <div class="main_container">
      <span style="padding-left: 0.75em">카테고리:</span>
      <div class="sub_container" v-for="(category, index) in categoryList.list" :key="index">
        <div class="category" @click="edit(category.id)">{{category.category}}</div>
        <div class="sub_category_container" v-for="(subCategory, index) in category.children" :key="index">
          <div class="category sub_category" @click="edit(subCategory.id)">- {{subCategory.category}}</div>
        </div>
      </div>
      <div class="btn_container">
        <b-button class="button" variant="primary" @click="add">추가</b-button>
        <b-button class="button" variant="danger" @click="cancel">취소</b-button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  async created() {
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');
  },
  computed: {
    categoryList() {
      return this.$store.state.asideCategoryList;
    }
  },
  methods: {
    add() {
      this.$router.push("/manage/category/edit")
    },
    edit(categoryId) {
      this.$router.push(`/manage/category/edit/${categoryId}`)
    },
    cancel() {
      this.$router.push("/manage");
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
      padding: 0.25em 0.625em;
      .category {
        width: 100%;
        padding: 0.25em 0.75em;
        border: 1px solid;
        cursor: pointer;
        &:hover {
          color: #007afd;
        }
      }
      .sub_category_container {
        padding-top: 0.5em;
        .sub_category {
          padding-left: 1em;
        }
      }
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
      }
    }
  }
}
</style>