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


    document.title = `'${this.$store.state.categoryData.search}'의 검색결과`;
  },
};
</script>

<style scoped>

</style>