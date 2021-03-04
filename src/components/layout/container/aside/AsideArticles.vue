<template>
  <div class="box-recent">
    <h3 class="title-sidebar blind">최근글과 인기글</h3>
    <ul class="tab-recent">
      <li class="tab-button recent_button" :class="{ on : !toggle}">
        <a class="tab-button" @click="toggleOff">최근글</a>
      </li>
      <li class="tab-button sidebar_button" :class="{ on : toggle }">
        <a class="tab-button" @click="toggleOn">인기글</a>
      </li>
    </ul>
    <ul class="list-recent" style="display: block" v-if="!toggle">
      <li v-for="(item, index) in data.recentArticles" :key="index">
        <a :href="`/${item.id}`" class="link-recent">
          <p class="thumbnail" :style="{backgroundImage: `url(${!item.thumbnail ? noImage : item.thumbnail})`}"></p>
          <div class="box-recent">
            <strong>{{ item.title }}</strong>
            <span>{{ item.simpleDate }}</span>
          </div>
        </a>
      </li>
    </ul>
    <ul class="list-recent list-tab" style="display: block" v-else>
      <li v-for="(item, index) in data.popularArticles" :key="index">
        <a :href="`/${item.id}`" class="link-recent">
          <p class="thumbnail" :style="{backgroundImage: `url(${!item.thumbnail ? noImage : item.thumbnail})`}"></p>
          <div class="box-recent">
            <strong>{{ item.title }}</strong>
            <span>{{ item.simpleDate }}</span>
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import noImage from '@/assets/no-image.jpg';

export default {
  created() {
    this.$store.dispatch('GET_ASIDE_ARTICLES');
  },
  computed: {
    data() {
      return this.$store.state.asideArticles;
    }
  },
  data() {
    return {
      toggle: false,
      noImage,
    }
  },
  methods: {
    toggleOff() {
      this.toggle = false;
    },
    toggleOn() {
      this.toggle = true;
    }
  }
};
</script>

<style scoped>
.tab-button {
  cursor: pointer;
}
</style>