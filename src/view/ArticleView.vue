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
  created() {
    this.$store.state.isViewPage = true;
    this.$store.state.isMainPage = false;
    this.$store.state.isCategoryPage = false;
    document.body.id = "tt-body-page";

    this.$store.dispatch('GET_ARTICLE_DATA', this.$route.params.articleId);
    this.$store.dispatch('GET_COMMENT_DATA', {
      articleId: this.$route.params.articleId
    });
  }
};
</script>

<style scoped>

</style>