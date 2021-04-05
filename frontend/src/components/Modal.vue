<template>
  <div>
    <modal name="reply" :adaptive="true">
      <div class="reply-modal">
        <AreaWrite v-on:submitData="submitReply"></AreaWrite>
      </div>
    </modal>
    <modal name="delete" :adaptive="true" :width="450">
      <DeleteComment v-on:cancel="deleteCancel" v-on:delete="deleteSubmit"></DeleteComment>
    </modal>
  </div>
</template>

<script>
import AreaWrite from '@/components/AreaWrite';
import DeleteComment from '@/components/DeleteComment';

export default {
  components: {
    AreaWrite, DeleteComment
  },
  methods: {
    // 답글을 보낸다
    async submitReply(data) {
      try {
        await this.$store.dispatch('SUBMIT_COMMENT_DATA', {
          articleId: this.$route.params.articleId,
          commentData: data,
          parentCommentId: this.$store.state.parentCommentId
        });
      } catch (error) {
        alert('댓글 등록에 실패하였습니다. 네트워크 상태를 확인해주세요.');
      } finally {
        this.$store.state.parentCommentId = null;
        this.$modal.hide("reply");
      }
    },
    deleteCancel() {
      this.$modal.hide('delete');
    },
    async deleteSubmit(password) {
      try {
        await this.$store.dispatch('DELETE_COMMENT_DATA', {
          commentId: this.$store.state.deleteCommentId,
          password,
        });
      } catch (error) {
        if (error.response.status === 403) {
          alert("댓글을 삭제할 수 없습니다. 비밀번호를 다시 확인해주세요.");
        } else {
          alert("실패하였습니다. 네트워크 상태를 확인해주세요.");
        }
      } finally {
        this.$store.state.deleteCommentId = null;
        this.$modal.hide('delete');
      }
    }
  }
};
</script>

<style scoped>
.reply-modal {
  padding: 30px;
}
</style>