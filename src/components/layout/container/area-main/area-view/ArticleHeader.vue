<template>
  <div class="article-header" :style="{ backgroundImage: `url(${data.cover_item_thumbnail})` }">
    <div class="inner-header">
      <div class="box-meta">
        <p class="category">{{ data.cover_item_category }}</p>
        <h2 class="title-article">{{ data.cover_item_title }}</h2>
        <div class="box-info">
          <span class="writer">{{ data.writer }} </span>
          <span class="date">{{ data.cover_item_simple_date }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ArticleHeader',
  computed: {
    data() {
      return this.$store.state.articleData;
    }
  }
};
</script>

<style scoped>

</style>