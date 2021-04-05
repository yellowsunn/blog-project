<template>
  <li class="item-reply rp_general">
    <i class="fas fa-user-circle thumbnail" v-if="!comment.isManager"></i>
    <span class="thumbnail" :style="{backgroundImage: `url(${ !this.managerProfile ? noImage : this.managerProfile })`}" v-else></span>
    <div class="box-content">
      <div class="box-meta">
        <strong>{{ comment.name }}</strong>
        <span class="date">{{ comment.date }}</span>
      </div>
      <p class="text" v-html="comment.content"></p>
      <a class="link-comment" @click="replyEvent(comment.commentId)">답글</a>

      <!-- 댓글 삭제 -->
      <div class="box-modify">
        <i class="far fa-times-circle" @click="deleteEvent(comment.commentId)" v-if="!comment.isManager || isAuthorized"></i>
      </div>
    </div>

    <ul class="list-reply-comment" v-if="subComment && subComment.length > 0">
      <Reply v-for="(reply, index) in subComment" :reply="reply" :key="index"></Reply>
    </ul>
  </li>
</template>

<script>
import Reply from '@/components/Reply';
import noImage from '@/assets/profile.png';

export default {
  components: { Reply },
  props: {
    comment: Object,
  },
  computed: {
    isAuthorized() {
      return this.$store.state.isAuthorized;
    },
    managerProfile() {
      return this.$store.state.asideProfileData.profileImage;
    },
    subComment() {
      return this.comment.subComment;
    }
  },
  data() {
    return {
      noImage
    }
  },
  methods: {
    replyEvent(commentId) {
      this.$store.state.parentCommentId = commentId;
      this.$modal.show('reply');
    },
    async deleteEvent(commentId) {
      this.$store.state.deleteCommentId = commentId;

      // 관리자인 경우
      if (this.isAuthorized) {
        const isDelete = confirm("해당 댓글을 삭제하시겠습니까?");
        if (isDelete) {
          try {
            await this.$store.dispatch('DELETE_COMMENT_DATA', {
              commentId: this.$store.state.deleteCommentId,
            });
          } catch (error) {
            alert("실패하였습니다. 네트워크 상태를 확인해주세요.");
          }
        }
      } else {
        this.$modal.show('delete');
      }
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