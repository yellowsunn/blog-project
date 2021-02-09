<!-- 메인 페이지에 뜨는 카테고리 게시글 목록입니다. -->
<template>
  <div class="area-cover area-cover-thumbnail">
    <div class="box-cover-title">
      <h2 class="title-cover">{{ categoryData.cover_title }}</h2>
      <a href="#" class="link-title">more</a>
    </div>
    <div class="box-article">
      <!-- 반복 -->
      <article class="article-type-common article-type-thumbnail checked-item"
               v-for="(data, index) in categoryData.item" :key="index">
        <a :href="data.cover_item_url" class="link-article">
          <p class="thumbnail" has-thumbnail="1" :style="{backgroundImage: `url(${data.cover_item_thumbnail})`}">
            <img src="" class="img-thumbnail" role="presentation">
          </p>
        </a>
        <div class="article-content">
          <a :href="data.cover_item_url" class="link-article">
            <strong class="title">{{ data.cover_item_title }}</strong>
            <p class="summary">{{ data.cover_item_summary }}</p>
          </a>
          <div class="box-meta">
            <a :href="data.cover_item_url" class="link-category">{{ data.cover_item_category }}</a>
            <span class="date">{{ data.cover_item_simple_date }}</span>
            <span class="reply">{{ data.cover_item_comment_count }}</span>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    categoryData: Object,
  }
};
</script>

<style scoped>

</style>