<!-- 메인페이지 상단에 노출되는 커버 게시글 -->
<template>
  <div class="area-cover">
    <h2 class="title-cover">{{ data.cover_title }}</h2>
    <div class="box-article">
      <article class="article-type-common article-type-crop checked-item">
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
      <button type="button" class="button-more">더보기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AreaCover',
  props: {
    data: Object,
  }
};
</script>

<style scoped>

</style>