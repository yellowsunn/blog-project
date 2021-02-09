<!-- 게시글 간략한 정보 -->
<template>
  <article class="article-type-common article-type-thumbnail checked-item">
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
</template>

<script>
export default {
  props: {
    data: Object,
  }
};
</script>

<style scoped>
</style>