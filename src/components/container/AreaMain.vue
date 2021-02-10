<template>
  <!-- area-main -->
  <div class="area-main">
    <!-- area-common -->
    <div class="area-common" v-if="isMainPage">
      <!-- 커버에 띄울 게시글 영역 -->
      <AreaCover :data="coverData"></AreaCover>

      <!-- 커버에 띄울 카테고리 목록 영역 -->
      <AreaCoverCategory :categoryData="coverCategoryData"></AreaCoverCategory>
    </div>

    <!-- s_list / 카테고리, 검색 리스트 -->
    <Category :categoryData="categoryData" v-if="isCategoryPage"></Category>

    <!-- area-view / 뷰페이지 및 기본 영역 -->
    <AreaView v-if="!isMainPage"></AreaView>
  </div>
</template>

<script>
import AreaCover from '@/components/container/area-main/AreaCover';
import AreaCoverCategory from '@/components/container/area-main/AreaCoverCategory';
import Category from '@/components/container/area-main/Category';
import AreaView from '@/components/container/area-main/AreaView';

export default {
  components: {
    AreaView,
    AreaCover, AreaCoverCategory,
    Category,
  },
  computed: {
    isMainPage() {
      return this.$store.state.isMainPage;
    },
    isCategoryPage() {
      return this.$store.state.isCategoryPage;
    },
    coverData() {
      return this.$store.state.coverData;
    },
    coverCategoryData() {
      return this.$store.state.coverCategoryData;
    },
    categoryData() {
      return this.$store.state.categoryData;
    }
  },
  props: {
    cover_item_url: String,
    cover_item_thumbnail: String,
  },
};
</script>

<style scoped>
/*.archives::before {*/
/*  content: "'";*/
/*}*/
/*.archives::after {*/
/*  content: "'의 검색결과";*/
/*}*/
</style>