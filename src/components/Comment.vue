<template>
  <li class="item-reply rp_general">
    <i class="fas fa-user-circle thumbnail"></i>
    <div class="box-content">
      <div class="box-meta">
        <strong>{{ comment.name }}</strong>
        <span class="date">{{ comment.date }}</span>
      </div>
      <p class="text" v-html="comment.content"></p>
      <a class="link-comment" @click="replyEvent">답글</a>

      <!-- 댓글 삭제 -->
      <div class="box-modify">
        <i class="far fa-times-circle" @click="deleteEvent"></i>
      </div>
    </div>

    <ul class="list-reply-comment" v-if="comment.subComment">
      <Reply v-for="(reply, index) in comment.subComment" :reply="reply" :key="index"></Reply>
    </ul>
  </li>
</template>

<script>
import Reply from '@/components/Reply';

export default {
  components: { Reply },
  props: {
    comment: Object,
  },
  methods: {
    replyEvent() {
      this.$modal.show('reply');
    },
    deleteEvent() {
      this.$modal.show('delete');
    }
  }
};
</script>

<style scoped>
a {
  cursor: pointer;
}

.fa-times-circle {
  color: #747474;
  padding-right: 10px;
  cursor: pointer;
}

.fa-user-circle {
  font-size: 46px;
  color: #c6c6c6;
  display: flex;
  justify-content: center;
  align-items: center;
}

@media screen and (max-width: 1060px) {
  .fa-user-circle {
    font-size: 37px;
  }
}
</style>