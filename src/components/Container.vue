<template>
  <div id="container">
    <main class="main">
      <Main></Main>
      <Aside></Aside>
    </main>
  </div>
</template>

<script>
import Main from '@/components/Main';
import Aside from '@/components/Aside';
export default {
  name: 'Container',
  components: { Aside, Main },
};
</script>

<style scoped>

</style>