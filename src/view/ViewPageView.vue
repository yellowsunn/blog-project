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
  }
};
</script>

<style scoped>

</style>