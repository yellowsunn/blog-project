<template>
  <div class="area-paging-more" v-if="isMobile && $route.path.startsWith('/category')">
    <a class="paging-more" :class="{ 'paging-more-loading' : loadingPage }" v-if="pageData.hasNext" @click="loadMorePage(pageData.pageNumber + 1)">더보기</a>
  </div>
  <div class="area-paging" v-else>
    <a @click="findArticleByPage(pageData.pageNumber - 1)" class="link_page link_prev" :class="{'no-more-prev' : pageData.isFirst }"><span class="xi-angle-left-min"></span></a>
    <div class="paging_num">
      <!-- 첫 페이지 -->
      <template v-if="!pageData.isFirst">
        <a class="link_num" @click="findArticleByPage(0)"><span>1</span></a>
      </template>

      <template v-if="beforePage.length > 0 && beforePage[0] > 1">
        <a class="link_num" style="cursor: text"><span>···</span></a>
      </template>
      <template v-if="beforePage.length > 0">
        <a class="link_num" @click="findArticleByPage(page)" v-for="page in beforePage" :key="page"><span>{{ page + 1 }}</span></a>
      </template>

      <!-- 현재 페이지 -->
      <a class="link_num" style="cursor: text"><span class="selected">{{ pageData.pageNumber + 1 }}</span></a>

      <template v-if="afterPage.length > 0">
        <a class="link_num" @click="findArticleByPage(page)" v-for="page in afterPage" :key="page"><span>{{ page + 1 }}</span></a>
      </template>
      <template v-if="afterPage.length > 0 && afterPage[afterPage.length - 1] < lastPage - 1">
        <a class="link_num" style="cursor: text"><span>···</span></a>
      </template>

      <!-- 마지막 페이지 -->
      <template v-if="!pageData.isLast">
        <a class="link_num" @click="findArticleByPage(lastPage)"><span>{{ lastPage + 1 }}</span></a>
      </template>
    </div>
    <a @click="findArticleByPage(pageData.pageNumber + 1)" class="link_page link_next" :class="{ 'no-more-next' : pageData.isLast }"><span class="xi-angle-right-min"></span></a>
  </div>
</template>

<script>
export default {
  props: {
    pageData: Object,
  },
  data() {
    const mql = window.matchMedia("screen and (max-width: 1060px)");
    return {
      mql,
      isMobile: mql.matches,
    }
  },
  computed: {
    loadingPage() {
      return this.$store.state.loadPage;
    },
    lastPage() {
      return this.pageData.totalPages - 1;
    },
    beforePage() {
      const pageNumber = this.pageData.pageNumber;
      // const pageNumber = 4;
      let start, end;
      if (pageNumber > 1) {
        end = pageNumber - 1;
        start = pageNumber - 4 >= 1 ? pageNumber - 4 : 1;
      }
      const arr = [];
      for (let i = start; i <= end; i++) {
        arr.push(i);
      }
      return arr;
    },
    afterPage() {
      const pageNumber = this.pageData.pageNumber;
      let start, end;
      if (pageNumber < this.lastPage - 1) {
        start = pageNumber + 1;
        end = pageNumber + 4 <= this.lastPage - 1 ? pageNumber + 4 : this.lastPage - 1;
      }
      const arr = [];
      for (let i = start; i <= end; i++) {
        arr.push(i);
      }
      return arr;
    }
  },
  mounted() {
    this.mql.addEventListener("change", e => {
      this.isMobile = e.matches;
    });
  },
  methods: {
    async findArticleByPage(page) {
      const url = `${window.location.protocol}//${window.location.host}`;
      if (this.$route.path.startsWith("/category")) {
        let categoryId = this.$route.params.categoryId;
        if (!categoryId) categoryId = '';
        window.location.href = `${url}/category/${categoryId}?page=${page}`
      } else if (this.$route.path.startsWith("/")) {
        try {
          const id = await this.$store.dispatch('GET_ARTICLE_ID', {
            categoryId: this.pageData.categoryId, page,
          });
          window.location.href = `${url}/${id.data}`
        } catch (error) {
          console.log(error);
        }
      }
    },
    async loadMorePage(page) {
      if (this.$route.path.startsWith("/category")) {
        let categoryId = this.$route.params.categoryId;
        if (!categoryId) categoryId = '';
        await this.$store.dispatch('UPDATE_CATEGORY_DATA', {
          categoryId: this.$route.params.categoryId,
          page: page,
        });
      }
    }
  }
};
</script>

<style scoped>
a {
  cursor: pointer;
}
</style>