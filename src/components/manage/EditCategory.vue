<template>
  <section>
    <div class="main_container">
      <div class="sub_container">
        <span class="label">카테고리 이름: </span><b-form-input class="header_input" v-model="categoryName"></b-form-input>
      </div>
      <div class="sub_container">
        <span class="label">부모 카테고리: </span>
        <b-form-select class="category_select" v-model="parentCategoryId" :options="options">
          <template #first>
            <b-form-select-option :value="null">카테고리 없음</b-form-select-option>
          </template>
        </b-form-select>
      </div>
      <div class="sub_container">
        <span class="label">카테고리 순서: </span><b-form-input type="number" class="header_input" v-model="order"></b-form-input>
      </div>
      <div class="btn_container">
        <b-button class="button" variant="primary" @click="submit">등록</b-button>
        <template v-if="this.categoryId">
          <b-button class="button" variant="warning" @click="deleteEvent">삭제</b-button>
        </template>
        <b-button class="button" variant="danger" @click="cancel">취소</b-button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  async created() {
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');

    // 카테고리를 수정하는 경우
    if (this.categoryId) {
      try {
        const data = await this.$store.dispatch('GET_CATEGORY_INFO', this.categoryId);
        this.id = data.id;
        this.categoryName = data.category;
        this.parentCategoryId = data.parentId ? data.parentId : null;
        this.order = data.order;
      } catch (error) {
        if (error.response.status === 401) {
          alert("블로그 관리자만 변경할 수 있습니다.");
          await this.$router.push('/login');
        } else {
          alert("예기치 못한 오류가 발생했습니다. 카테고리 정보를 불러올 수 없습니다.");
          await this.$router.push("/manage/category");
        }
      }
    }
    this.options = this.getOptions();
  },
  computed: {
    categoryId() {
      return this.$route.params.categoryId;
    }
  },
  data() {
    return {
      id: null,
      categoryName: "",
      parentCategoryId: null,
      order: 0,
      options: [],
    }
  },
  methods: {
    async submit() {
      const data = {
        id: this.id,
        category: this.categoryName,
        parentId: this.parentCategoryId,
        order: this.order
      };

      try {
        if (this.categoryId) {
          await this.$store.dispatch('UPDATE_CATEGORY', data);
        } else {
          await this.$store.dispatch('CREATE_CATEGORY', data);
        }
        await this.$router.push("/manage/category");
      } catch (error) {
        if (error.response.status === 401) {
          alert("블로그 관리자만 변경할 수 있습니다.");
          await this.$router.push('/login');
        } else {
          alert("예기치 못한 오류가 발생했습니다.");
          await this.$router.push("/manage/category");
        }
      }
    },
    async deleteEvent() {
      const isDelete = confirm("해당 카테고리를 삭제하시겠습니까?");
      if (isDelete) {
        try {
          await this.$store.dispatch('DELETE_CATEGORY', this.categoryId);
          await this.$router.push("/manage/category");
        } catch (error) {
          if (error.response.status === 401) {
            alert("블로그 관리자만 변경할 수 있습니다.");
            await this.$router.push('/login');
          } else {
            if (error.response.data.message.indexOf('ConstraintViolationException') >= 0) {
              alert("빈 카테고리만 삭제할 수 있습니다.");
            } else {
              alert("예기치 못한 오류가 발생했습니다.");
            }
            await this.$router.push("/manage/category");
          }
        }
      }
    },
    cancel() {
      this.$router.push("/manage/category");
    },
    getOptions() {
      const categories = this.$store.state.asideCategoryList.list;
      const options = [];
      for(let i = 0; i < categories.length; i++) {
        options.push({ value: categories[i].id, text: categories[i].category });
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