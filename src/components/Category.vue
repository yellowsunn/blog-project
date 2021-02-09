<template>
  <div class="area-common">
    <!-- use-category-banner 를 바디 class에 추가해야 보임 -->
    <div class="category-banner-wrp display-none">
      <div class="category-banner" style="background-image:url('https://blog.kakaocdn.net/dn/lPmqD/btqHJirPXDw/NrkUsEnxkt6mBJUErigq71/img.jpg')">
        <div class="category-banner-inner">
          <div class="category-description-box">
            <strong class="category-description">문화재청 소식을 전합니다.</strong>
          </div>
        </div>
      </div>
    </div>
    <h2 class="title-search article-title-thumbnail title-border" list-style="thumbnail">
      <b class="archives">{{ categoryData.list_conform }}</b> <span>{{ categoryData.list_count }}</span>
    </h2>
    <Article :data="data" v-for="(data, index) in categoryData.item" :key="index"></Article>
  </div>
</template>

<script>
import Article from '@/components/Article';
export default {
  components: { Article },
  props: {
    categoryData: Object,
  }
};
</script>

<style scoped>

</style>