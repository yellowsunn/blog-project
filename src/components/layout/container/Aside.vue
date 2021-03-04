<template>
  <aside class="area-aside" :class="{'area-aside-on' : asideOn }" v-click-outside="asideOffEvent">
    <!-- 프로필 -->
    <AsideProfile></AsideProfile>

    <!-- 카테고리 메뉴 -->
    <AsideCategoryList></AsideCategoryList>
    <!-- 최근글과 인기글 -->
    <AsideArticles></AsideArticles>

    <!-- search-bar for MOBILE -->
    <div class="util use-sidebar">
      <div class="search">
        <input class="searchInput" type="text" name="search" value="" placeholder="검색내용을 입력하세요." onkeypress="if (event.keyCode == 13) { requestSearch('.util.use-sidebar .searchInput') }">
      </div>
    </div>
  </aside>
</template>

<script>
import AsideProfile from '@/components/layout/container/aside/AsideProfile'
import AsideCategoryList from '@/components/layout/container/aside/AsideCategoryList'
import AsideArticles from '@/components/layout/container/aside/AsideArticles';

export default {
  components: {
    AsideProfile,
    AsideCategoryList,
    AsideArticles
  },
  computed: {
    asideOn() {
      return this.$store.state.asideOn;
    }
  },
  methods: {
    asideOffEvent() {
      if (this.$store.state.asideOn === true) {
        this.$store.state.asideOn = false;
        document.body.classList.remove('bg-dimmed');
        document.body.style.overflow = '';
      }
    }
  }
};
</script>

<style scoped>

</style>