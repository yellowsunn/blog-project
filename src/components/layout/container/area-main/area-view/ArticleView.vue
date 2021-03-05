<template>
  <div class="article-view">
    <div v-html="data.content"></div>

    <!-- 공감 아이콘 -->
    <div class="container_postbtn #post_button_group">
      <div class="postbtn_like" @click="likeClickEvent">
        <div class="wrap_btn">
          <button class="btn_post uoc-icon">
            <!-- 클래스 추가: empathy_up_without_ani like_on -->
            <div class="uoc-icon" :class="[{ 'empathy_up_without_ani' : data.isAlreadyLike }, { 'like on' : data.isAlreadyLike }]">
              <span class="ico_postbtn ico_like">좋아요</span>
              <span class="txt_like uoc-count">{{ data.like > 0 ? data.like : "공감" }}</span>
            </div>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    data() {
      return this.$store.state.articleData;
    },
  },
  methods: {
    likeClickEvent() {
      try {
        this.$store.dispatch('UPDATE_ARTICLE_LIKE', this.$route.params.articleId);
        const articleData = this.$store.state.articleData;

        articleData.isAlreadyLike = !articleData.isAlreadyLike;
        if (articleData.isAlreadyLike) {
          articleData.like += 1;
        } else {
          articleData.like -= 1;
        }
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>

<style scoped>

</style>