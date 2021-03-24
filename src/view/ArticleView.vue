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
  components: { Footer, Container, Header },
  async created() {
    this.$store.state.isViewPage = true;
    this.$store.state.isMainPage = false;
    this.$store.state.isCategoryPage = false;
    document.body.id = "tt-body-page";

    await this.$store.dispatch('GET_AUTHORITY');
    await this.$store.dispatch('GET_HEADER_DATA');
    await this.$store.dispatch('GET_ARTICLE_DATA', this.$route.params.articleId);
    await this.$store.dispatch('GET_COMMENT_DATA', {
      articleId: this.$route.params.articleId
    });
    await this.$store.dispatch('GET_ASIDE_PROFILE_DATA');
    await this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');
    await this.$store.dispatch('GET_ASIDE_ARTICLES');

    document.title = this.$store.state.articleData.title;
  }
};
</script>

<style scoped>

</style>