<template>
  <div>
    <Header></Header>
    <Container></Container>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from '@/components/HeaderComponent';
import Container from '@/components/Container';
import Footer from '@/components/Footer';
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