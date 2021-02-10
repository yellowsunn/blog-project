<template>
  <div class="area-paging" v-if="!isMobile">
    <a href="/category/?page=1" class="link_page link_prev "><span class="xi-angle-left-min"></span></a>
    <div class="paging_num">

      <a href="/category/?page=1" class="link_num"><span>1</span></a>

      <a class="link_num"><span class="selected">2</span></a>

      <a href="/category/?page=3" class="link_num"><span>3</span></a>

      <a href="/category/?page=4" class="link_num"><span>4</span></a>

    </div>
    <a href="/category/?page=3" class="link_page link_next "><span class="xi-angle-right-min"></span></a>
  </div>
  <div class="area-paging-more" v-else>
    <a href="#page-more" class="paging-more">더보기</a>
  </div>
</template>

<script>
export default {
  data() {
    const mql = window.matchMedia("screen and (max-width: 1060px)");
    return {
      mql,
      isMobile: mql.matches,
    }
  },
  mounted() {
    this.mql.addEventListener("change", e => {
      this.isMobile = e.matches;
    });
  }
};
</script>

<style scoped>

</style>