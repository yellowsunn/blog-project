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
  metaInfo() {
    let summary = this.$store.state.articleData.content;
    summary = summary.replace(/<([^>]+)>/gi, " ");
    summary = summary.replace(/\s+/gi, " ");
    summary = summary.replace(/^\s+|\s+$/gi, "");
    summary = summary.substring(0, 200);

    return {
      title: this.$store.state.articleData.title,
      meta: [
        { charset: 'utf-8' },
        { name: 'title', content: this.$store.state.articleData.title },
        { name: 'description', content: summary }
      ]
    }
  },
};
</script>

<style scoped>

</style>