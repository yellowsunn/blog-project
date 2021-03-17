<template>
  <div>
    <Header></Header>
    <Container></Container>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '@/components/layout/Footer';
import Container from '@/components/layout/Container';
import Header from '@/components/layout/Header';

export default {
  name: 'SearchView',
  components: { Header, Container, Footer },
  async created() {
    this.$store.state.isCategoryPage = true;

    document.body.id = "tt-body-search";
    document.body.classList.add('headerbannerdisplayon'); // 메인페이지 배너 이미지 사라짐

    await this.$store.dispatch('GET_HEADER_DATA');
    await this.$store.dispatch('GET_SEARCH_DATA', {
      search: this.$route.params.search,
      page: this.$route.query.page,
    });
    await this.$store.dispatch('GET_ASIDE_PROFILE_DATA');
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');
    await this.$store.dispatch('GET_ASIDE_ARTICLES');
  },
};
</script>

<style scoped>

</style>