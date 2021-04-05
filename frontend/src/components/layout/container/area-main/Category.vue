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
      <template v-if="startsWidthCategory">
        <b class="archives">{{ category || '전체 글' }}</b> <span>{{ categoryData.totalElements }}</span>
      </template>
      <template v-else>
        <b class="archives">{{ categoryData.search }}</b> <span>{{ categoryData.totalElements }}</span>
      </template>
    </h2>

    <template v-if="categoryData.articles">
      <Article :data="data" v-for="(data, index) in categoryData.articles" :key="index"></Article>
    </template>

    <!-- 해당 카테고리에 게시글이 없는 경우 -->
    <div v-else class="box-no-search type-category" :class="[{'type-category' : startsWidthCategory}, {'type-search' : startsWidthSearch}]">
      <template v-if="startsWidthCategory">
        <span>선택하신 카테고리에 해당하는 글이 없습니다.</span>
        <span>다른 카테고리를 선택하시거나, 검색 기능을 활용해 보세요.</span>
      </template>
      <template v-else>
        <span>입력하신 단어의 철자가 정확한지 확인해 보세요.</span>
        <span>검색어의 단어 수를 줄이거나, 보다 일반적인 단어로 검색해 보세요.</span>
        <span>두 단어 이상의 키워드로 검색 하신 경우, 정확하게 띄어쓰기를 한 후 검색해 보세요.</span>
      </template>
    </div>
  </div>
</template>

<script>
import Article from '@/components/Article';
export default {
  components: { Article },
  props: {
    categoryData: Object,
  },
  computed: {
    category() {
      const parentCategory = this.categoryData.parentCategory;
      const category = this.categoryData.category;
      if (!category) return '';
      if (!parentCategory) return category;
      return `${parentCategory}/${category}`;
    },
    startsWidthCategory() {
      return this.$route.path.startsWith("/category");
    },
    startsWidthSearch() {
      return this.$route.path.startsWith("/search");
    }
  }
};
</script>

<style scoped>

</style>