<!-- 게시글 간략한 정보 -->
<template>
  <article class="article-type-common article-type-thumbnail checked-item">
    <a :href="`/${data.id}`" class="link-article">
      <p class="thumbnail" has-thumbnail="1" :style="{backgroundImage: `url(${data.thumbnail})`}">
        <img src="" class="img-thumbnail" role="presentation">
      </p>
    </a>
    <div class="article-content">
      <a :href="`/${data.id}`" class="link-article">
        <strong class="title">{{ data.title }}</strong>
        <p class="summary">{{ data.summary }}</p>
      </a>
      <div class="box-meta">
        <a :href="`/category/${data.categoryId}`" class="link-category">{{ category }}</a>
        <span class="date">{{ data.simpleDate }}</span>
        <span class="reply">{{ data.commentCount }}</span>
      </div>
    </div>
  </article>
</template>

<script>
export default {
  props: {
    data: Object,
  },
  computed: {
    category() {
      const parentCategory = this.data.parentCategory;
      if (!parentCategory) return this.data.category;
      return `${parentCategory}/${this.data.category}`;
    }
  }
};
</script>

<style scoped>
</style>