<template>
  <div class="article-tag">
    <h3 class="title-footer" v-if="!isEmpty">Tag</h3>
    <div class="box-tag">
      <a :href="`/tag/${tag}`" rel="tag" v-for="(tag, index) in data.tags" :key="index">{{ tag }}</a>,
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    data() {
      return this.$store.state.articleData;
    },
    isEmpty() {
      return this.data.tags === undefined || this.data.tags.length === 0;
    }
  },
};
</script>

<style scoped>

</style>