<template>
  <!-- area-main -->
  <div class="area-main">

    <!-- s_list / 카테고리, 검색 리스트 -->
    <div class="area-common">

    </div>

    <!-- area-view / 뷰페이지 및 기본 영역 -->
    <div class="area-view"></div>

    <!-- area-common -->
    <div class="area-common">

      <!-- 커버에 띄울 게시글 영역 -->
      <AreaCover :data="coverData"></AreaCover>

      <!-- type-thumbnail -->
      <!-- 커버에 띄울 카테고리 목록 영역 -->
      <AreaCoverCategory :categoryData="coverCategoryData"></AreaCoverCategory>
    </div>
  </div>
</template>

<script>
import AreaCover from '@/components/AreaCover';
import AreaCoverCategory from '@/components/AreaCoverCategory';

export default {
  components: {
    AreaCover, AreaCoverCategory
  },
  computed: {
    coverData() {
      return this.$store.state.coverData;
    },
    coverCategoryData() {
      return this.$store.state.coverCategoryData;
    }
  },
  props: {
    cover_item_url: String,
    cover_item_thumbnail: String,
  },
};
</script>

<style scoped>

</style>