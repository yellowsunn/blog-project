<template>
  <div class="article-page">
    <h3 class="title-footer">'{{ category }}'의 다른글</h3>
    <ul>
      <li>
        <template v-if="data.before !== undefined">
          <a :href="`/${data.before.id}`"><span>이전글</span><strong>{{ data.before.title }}</strong></a>
        </template>
      </li>
      <li><span>현재글</span><strong>{{ data.title }}</strong></li>
      <li>
        <template v-if="data.after !== undefined">
          <a :href="`/${data.after.id}`"><span>다음글</span><strong>{{ data.after.title }}</strong></a>
        </template>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  computed: {
    data() {
      return this.$store.state.articleData;
    },
    category() {
      const parentCategory = this.data.parentCategory;
      if (!parentCategory) {
        return this.data.category;
      }
      return `${parentCategory}/${this.data.category}`;
    }
  }
};
</script>

<style scoped>

</style>