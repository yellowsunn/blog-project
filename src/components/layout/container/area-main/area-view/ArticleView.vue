<template>
  <div class="article-view">
    <div v-html="data.content"></div>

    <!-- 공감 아이콘 -->
    <div class="container_postbtn #post_button_group">
      <div class="postbtn_like">
        <div class="wrap_btn" @click="likeClickEvent">
          <button class="btn_post uoc-icon">
            <!-- 클래스 추가: empathy_up_without_ani like_on -->
            <div class="uoc-icon" :class="[{ 'empathy_up_without_ani' : data.isAlreadyLike }, { 'like on' : data.isAlreadyLike }]">
              <span class="ico_postbtn ico_like">좋아요</span>
              <span class="txt_like uoc-count">{{ data.like > 0 ? data.like : "공감" }}</span>
            </div>
          </button>
        </div>
        <template v-if="isAuthorized">
          <div class="wrap_btn" @click="editClickEvent">
            <button type="button" class="btn_post">
              <i class="far fa-edit"></i>
            </button>
          </div>
          <div class="wrap_btn">
            <button type="button" class="btn_post">
              <i class="far fa-trash-alt"></i>
            </button>
          </div>
        </template>
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
    isAuthorized() {
      return this.$store.state.isAuthorized;
    }
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
    },
    editClickEvent() {
      const url = `${window.location.protocol}//${window.location.host}`;
      window.location.href = `${url}/newpost/${this.$route.params.articleId}`;
    }
  }
};
</script>

<style scoped>
i {
  font-size: 14px;
  color: #525252;
  opacity: 55%;
}
</style>