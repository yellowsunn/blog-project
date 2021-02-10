<template>
  <div id="container">
    <main class="main">
      <AreaMain></AreaMain>
      <Aside></Aside>
    </main>
  </div>
</template>

<script>
import AreaMain from '@/components/container/AreaMain';
import Aside from '@/components/container/Aside';

export default {
  name: 'Container',
  components: { Aside, AreaMain },
};
</script>

<style scoped>

</style>