<!-- 게시글 내용이 보이는 뷰페이지 -->
<template>
  <div class="area-view">
    <!-- 이미지가 없는 경우 article-header-noimg -->
    <ArticleHeader v-if="isViewPage"></ArticleHeader>

    <!-- 에디터 영역 -->
    <ArticleView v-if="isViewPage"></ArticleView>

    <!-- article-footer -->
    <ArticleFooter v-if="isViewPage"></ArticleFooter>

    <!-- area-paging -->
    <AreaPage :pageData="pageData"></AreaPage>
  </div>
</template>

<script>
import AreaPage from '@/components/layout/container/area-main/area-view/AreaPage';
import ArticleHeader from '@/components/layout/container/area-main/area-view/ArticleHeader';
import ArticleView from '@/components/layout/container/area-main/area-view/ArticleView';
import ArticleFooter from '@/components/layout/container/area-main/area-view/ArticleFooter';

export default {
  name: 'AreaView',
  components: {
    ArticleFooter,
    ArticleView,
    AreaPage,
    ArticleHeader
  },
  computed: {
    isViewPage() {
      return this.$store.state.isViewPage;
    },
    pageData() {
      if (this.$route.path.startsWith("/category")) {
        return this.$store.state.categoryData;
      } else if (this.$route.path.startsWith("/")) {
        return this.$store.state.articleData;
      }
      return {};
    }
  },
};
</script>

<style scoped>

</style>