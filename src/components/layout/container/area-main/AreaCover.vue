<!-- 메인페이지 상단에 노출되는 커버 게시글 -->
<template>
  <div class="area-cover">
    <h2 class="title-cover">{{ !data.parentCategory ? data.category : data.parentCategory }}</h2>
    <div class="box-article">
      <article class="article-type-common article-type-crop checked-item">
        <a :href="data.id" class="link-article">
          <p class="thumbnail" has-thumbnail="1" v-if="data.thumbnail" :style="{backgroundImage: `url(${ data.thumbnail })`}">
            <img src="" class="img-thumbnail" role="presentation">
          </p>

          <p class="thumbnail" has-thumbnail="1" v-else style="background-image: url(/src/assets/no-image.jpg)}">
            <img src="" class="img-thumbnail" role="presentation">
          </p>
        </a>
        <div class="article-content">
          <a :href="data.id" class="link-article">
            <strong class="title">{{ data.title }}</strong>
            <p class="summary">{{ data.summary }}</p>
          </a>
          <div class="box-meta">
            <a :href="data.id" class="link-category">{{ data.category }}</a>
            <span class="date">{{ data.simpleDate }}</span>
            <span class="reply">{{ data.commentCount }}</span>
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
  },
  data() {
    return {
      noImage: '@/assets/no-image.jpg',
    }
  }
};
</script>

<style scoped>

</style>