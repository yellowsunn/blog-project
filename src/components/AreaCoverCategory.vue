<!-- 메인 페이지에 뜨는 카테고리 게시글 목록입니다. -->
<template>
  <div class="area-cover area-cover-thumbnail">
    <div class="box-cover-title">
      <h2 class="title-cover">{{ categoryData.cover_title }}</h2>
      <a href="#" class="link-title">more</a>
    </div>
    <div class="box-article">
      <Article :data="data" v-for="(data, index) in categoryData.item" :key="index"></Article>
    </div>
  </div>
</template>

<script>
import Article from '@/components/Article';
export default {
  components: { Article },
  props: {
    categoryData: Object,
  }
};
</script>

<style scoped>

</style>