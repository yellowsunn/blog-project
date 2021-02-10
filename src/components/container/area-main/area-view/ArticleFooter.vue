<template>
  <div class="article-footer">
    <!-- article-tag -->
    <ArticleTag></ArticleTag>
    <!-- article-page -->
    <ArticlePage></ArticlePage>
    <!-- article-reply -->
    <ArticleReply></ArticleReply>
  </div>

</template>

<script>
import ArticleTag from '@/components/container/area-main/area-view/article-footer/ArticleTag';
import ArticlePage from '@/components/container/area-main/area-view/article-footer/ArticlePage';
import ArticleReply from '@/components/container/area-main/area-view/article-footer/ArticleReply';
export default {
  name: 'ArticleFooter',
  components: { ArticleReply, ArticlePage, ArticleTag },
};
</script>

<style scoped>

</style>