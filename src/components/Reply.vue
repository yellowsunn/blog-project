<template>
  <li class="item-reply rp_general">
    <i class="fas fa-user-circle thumbnail"></i>
    <div class="box-content">
      <div class="box-meta">
        <strong>닉네임</strong>
        <span class="date">2021.02.13 23:40</span>
      </div>
      <p class="text">
        예제 텍스트입니다.<br> 예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>
      </p>
      <a class="link-comment" @click="replyEvent">답글</a>

      <!-- 댓글 삭제 -->
      <div class="box-modify">
        <i class="far fa-times-circle" @click="deleteEvent"></i>
      </div>
    </div>

    <ul class="list-reply-comment">
      <SubReply></SubReply>
    </ul>

    <!-- 모달 상자 -->
    <modal name="reply" :adaptive="true">
      <div class="reply-modal">
        <AreaWrite v-on:submit="replySubmit"></AreaWrite>
      </div>
    </modal>
    <modal name="delete" :adaptive="true" :width="450">
      <DeleteComment v-on:cancel="deleteCancel" v-on:submit="deleteSubmit"></DeleteComment>
    </modal>
  </li>
</template>

<script>
import SubReply from '@/components/SubReply';
import AreaWrite from '@/components/AreaWrite';
import DeleteComment from '@/components/DeleteComment';

export default {
  components: { DeleteComment, SubReply, AreaWrite },
  methods: {
    /** 모달 이벤트 **/
    replyEvent() {
      this.$modal.show('reply')
    },
    // replySubmit(event) {
    replySubmit() {
      this.$modal.hide("reply");
    },
    deleteEvent() {
      this.$modal.show('delete');
    },
    deleteCancel() {
      this.$modal.hide('delete');
    },
    deleteSubmit(event) {
      console.log(event);
      this.$modal.hide('delete');
    }
    /**************/
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

.reply-modal {
  padding: 30px;
}
</style>