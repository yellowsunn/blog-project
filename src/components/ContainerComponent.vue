<template>
  <Container>
  </Container>
</template>

<script>
import Container from '@/components/Container';
export default {
  name: 'ContainerComponent',
  components: { Container },
  data() {
    return {
      list_conform: '분류 전체보기',
      list_count: 35,
    }
  }
};
</script>

<style scoped>

</style>