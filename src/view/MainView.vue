<template>
  <div>
    <Header></Header>
    <Container></Container>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from '@/components/HeaderComponent';
import Container from '@/components/ContainerComponent';
import Footer from '@/components/Footer';

export default {
  name: 'MainView',
  components: { Footer, Container, Header },
};
</script>

<style lang="scss" scoped>
</style>