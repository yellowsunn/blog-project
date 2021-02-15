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
  created() {
    this.$store.state.isMainPage = true;
    this.$store.state.isCategoryPage = false;
    this.$store.state.isViewPage = false;
  }
};
</script>

<style lang="scss" scoped>
</style>