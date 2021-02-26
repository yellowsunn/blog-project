<template>
  <div class="article-reply">
    <div class="box-total" :class="{'comment-border-none' : !isCommentFirst}">
      <a>댓글 <span>{{ data.totalElements }}</span></a>

      <a class="paging-more comment-page-more" @click="loadMoreComments" v-if="!isCommentFirst">{{ loadingText }}</a>
    </div>
    <div>
      <!-- area-reply -->
      <div class="area-reply">
        <ul class="list-reply">
          <!-- 반복 -->
          <Comment v-for="(comment, index) in data.content" :comment="comment" :key="index"></Comment>
        </ul>
      </div>

      <!-- AreaWrite -->
      <AreaWrite></AreaWrite>
    </div>

    <Modal></Modal>
  </div>
</template>

<script>
import Comment from '@/components/Comment';
import AreaWrite from '@/components/AreaWrite';
import Modal from '@/components/Modal';

export default {
  name: 'ArticleReply',
  components: { Modal, Comment, AreaWrite },
  computed: {
    data() {
      return this.$store.state.commentData;
    },
    isCommentFirst() {
      return this.$store.getters.isCommentFirst;
    },
    loadingText() {
      return this.$store.state.loadComments ? '이전 댓글을 불러오는 중입니다' : '이전 댓글 더보기';
    }
  },
  methods: {
    loadMoreComments() {
      this.$store.dispatch('GET_COMMENT_DATA', {
        articleId: this.$route.params.articleId,
        page: this.data.number - 1,
      })
    }
  }
};
</script>

<style lang="scss" scoped>
.comment-page-more {
  margin-top: 21px;
  border: 1px solid #eee;
  color: #999 !important;
  &:hover {
    text-decoration: underline;
  }
  cursor: pointer;
}
.comment-border-none {
  border-bottom: 0;
  padding-bottom: 0;
}
</style>