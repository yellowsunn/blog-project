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
  name: 'MainView',
  components: { Footer, Container, Header },
  async created() {
    this.$store.state.isMainPage = true;
    this.$store.state.isCategoryPage = false;
    this.$store.state.isViewPage = false;

    await this.$store.dispatch('GET_HEADER_DATA');
    await this.$store.dispatch('GET_MAIN_PAGE_DATA');
    await this.$store.dispatch('GET_ASIDE_PROFILE_DATA');
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');
    await this.$store.dispatch('GET_ASIDE_ARTICLES');
  }
};
</script>

<style lang="scss" scoped>
</style>