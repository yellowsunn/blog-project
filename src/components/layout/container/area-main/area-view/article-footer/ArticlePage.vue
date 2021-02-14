<template>
  <div class="article-page">
    <h3 class="title-footer">'{{ data.cover_item_category }}'의 다른글</h3>
    <ul>
      <li>
        <template v-if="data.before !== undefined">
          <a :href="`/${data.before.link}`"><span>이전글</span><strong>{{ data.before.title }}</strong></a>
        </template>
      </li>
      <li><span>현재글</span><strong>{{ data.cover_item_title }}</strong></li>
      <li>
        <template v-if="data.after !== undefined">
          <a :href="`/${data.after.link}`"><span>다음글</span><strong>{{ data.after.title }}</strong></a>
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
  }
};
</script>

<style scoped>

</style>