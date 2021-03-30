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
  metaInfo() {
    return {
      title: this.$store.state.coverHeaderData.title,
      meta: [
        { charset: 'utf-8' },
        { name: 'title', content: this.$store.state.coverHeaderData.title },
        { name: 'description', content: this.$store.state.asideProfileData.profileText }
      ]
    }
  }
};
</script>

<style lang="scss" scoped>
</style>