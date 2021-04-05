<template>
  <li class="item-reply rp_general">
    <i class="fas fa-user-circle thumbnail" v-if="!reply.isManager"></i>
    <span class="thumbnail" :style="{backgroundImage: `url(${ !this.managerProfile ? noImage : this.managerProfile })`}" v-else></span>
    <div class="box-content">
      <div class="box-meta">
        <strong>{{ reply.name }}</strong>
        <span class="date">{{ reply.date }}</span>
      </div>
      <p class="text" v-html="reply.content"></p>

      <!-- 댓글 삭제 -->
      <div class="box-modify">
        <i class="far fa-times-circle" @click="deleteEvent(reply.commentId)" v-if="!reply.isManager || isAuthorized"></i>
      </div>
    </div>
  </li>
</template>

<script>
import noImage from '@/assets/profile.png';

export default {
  props: {
    reply: Object,
  },
  computed: {
    isAuthorized() {
      return this.$store.state.isAuthorized;
    },
    managerProfile() {
      return this.$store.state.asideProfileData.profileImage;
    },
  },
  data() {
    return {
      noImage
    }
  },
  methods: {
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