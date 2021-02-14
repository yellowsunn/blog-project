<template>
  <div class="article-reply">
    <div class="box-total">
      <a href="#">댓글 <span>{{ data.total }}</span></a>
    </div>
    <div>
      <!-- area-reply -->
      <div class="area-reply">
        <ul class="list-reply">
          <!-- 반복 -->
          <Comment v-for="(comment, index) in data.item" :comment="comment" :key="index"></Comment>
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
    }
  }
};
</script>

<style scoped>
</style>