<template>
  <div>
    <Header></Header>
    <Container></Container>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from '@/components/layout/Header';
import Container from '@/components/layout/Container';
import Footer from '@/components/layout/Footer';

export default {
  name: 'CategoryView',
  components: { Footer, Container, Header },
  metaInfo() {
    return {
      title: `${this.category() || '전체글'}`,
      titleTemplate: `'%s' 카테고리의 글 목록`
    }
  },
  methods: {
    category() {
      const categoryData = this.$store.state.categoryData;
      const parentCategory = categoryData.parentCategory;
      const category = categoryData.category;
      if (!category) return '';
      if (!parentCategory) return category;
      return `${parentCategory}/${category}`;
    }
  }
};
</script>

<style scoped>
</style>