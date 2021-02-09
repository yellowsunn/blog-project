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
  name: 'CategoryView',
  components: { Footer, Container, Header },
  created() {
    this.$store.state.isCategoryPage = true;
    this.$store.state.isMainPage = false;
  }
};
</script>

<style scoped>
</style>