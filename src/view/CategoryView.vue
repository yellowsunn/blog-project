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
    this.$store.state.isViewPage = false;
    document.body.id = "tt-body-category"
    document.body.classList.add('headerbannerdisplayon'); // 메인페이지 배너 이미지 사라짐
  }
};
</script>

<style scoped>
</style>