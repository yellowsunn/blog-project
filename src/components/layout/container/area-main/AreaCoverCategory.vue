<!-- 메인 페이지에 뜨는 카테고리 게시글 목록입니다. -->
<template>
  <div class="area-cover area-cover-thumbnail">
    <div class="box-cover-title">
      <h2 class="title-cover">{{ category || '전체 글' }}</h2>
      <a :href="`/category/${categoryData.id || ''}`" class="link-title">more</a>
    </div>
    <div class="box-article">
      <Article :data="data" v-for="(data, index) in categoryData.articles" :key="index"></Article>
    </div>
  </div>
</template>

<script>
import Article from '@/components/Article';
export default {
  components: { Article },
  props: {
    categoryData: Object,
  },
  computed: {
    // category() {
    //   const base = this.categoryData.baseCategory;
    //   const child = this.categoryData.category;
    //   if (base === child) return base;
    //   else return `${base}/${child}`;
    // },
    category() {
      const parentCategory = this.categoryData.parentCategory;
      const category = this.categoryData.category;
      if (!category) return '';
      if (!parentCategory) return category;
      return `${parentCategory}/${category}`;
    }
  }
};
</script>

<style scoped>

</style>