<template>
  <li class="item-reply rp_general">
    <i class="fas fa-user-circle thumbnail"></i>
    <div class="box-content">
      <div class="box-meta">
        <strong>{{ reply.name }}</strong>
        <span class="date">{{ reply.date }}</span>
      </div>
      <p class="text" v-html="reply.content"></p>

      <!-- 댓글 삭제 -->
      <div class="box-modify">
        <i class="far fa-times-circle" @click="deleteEvent(reply.commentId)"></i>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: {
    reply: Object,
  },
  methods: {
    deleteEvent(commentId) {
      console.log("delete : " + commentId);
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
  padding-right: 5px;
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

@media screen and (max-width: 767px) {
  .fa-times-circle {
    padding-right: 10px;
  }
}
</style>