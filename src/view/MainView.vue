<template>
  <div>
    <Header></Header>
    <Container></Container>
  </div>
</template>

<script>
import Header from '@/components/HeaderComponent';
import Container from '@/components/ContainerComponent';

export default {
  name: 'MainView',
  components: { Container, Header },
};
</script>

<style lang="scss" scoped>
</style>