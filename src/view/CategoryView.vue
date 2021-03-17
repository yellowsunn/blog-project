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
  async created() {
    this.$store.state.isCategoryPage = true;
    this.$store.state.isMainPage = false;
    this.$store.state.isViewPage = false;
    document.body.id = "tt-body-category"
    document.body.classList.add('headerbannerdisplayon'); // 메인페이지 배너 이미지 사라짐

    await this.$store.dispatch('GET_HEADER_DATA');
    await this.$store.dispatch('GET_CATEGORY_DATA', {
      categoryId: this.$route.params.categoryId,
      page: this.$route.query.page,
    });
    await this.$store.dispatch('GET_ASIDE_PROFILE_DATA');
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');
    await this.$store.dispatch('GET_ASIDE_ARTICLES');
  }
};
</script>

<style scoped>
</style>