<template>
  <div class="article-header" :style="{ backgroundImage: `url(${data.thumbnail})` }">
    <div class="inner-header">
      <div class="box-meta">
        <p class="category">{{ category }}</p>
        <h2 class="title-article">{{ data.title }}</h2>
        <div class="box-info">
          <span class="writer">{{ data.writer }} </span>
          <span class="date">{{ data.date }}</span>
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
    },
    category() {
      const parentCategory = this.data.parentCategory;
      if (parentCategory) {
        return `${parentCategory}/${this.data.category}`;
      }
      return this.data.category;
    }
  }
};
</script>

<style scoped>

</style>