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
      <!-- 댓글 삭제 -->
      <div class="box-modify">
        <i class="far fa-times-circle" @click="deleteEvent"></i>
      </div>
    </div>

    <!-- 모달 상자 -->
    <modal name="subDelete" :adaptive="true" :width="450">
      <DeleteComment v-on:cancel="deleteCancel" v-on:submit="deleteSubmit"></DeleteComment>
    </modal>
  </li>
</template>

<script>
import DeleteComment from '@/components/DeleteComment'

export default {
  components: {
    DeleteComment
  },
  methods: {
    /** 모달 이벤트 **/
    deleteEvent() {
      this.$modal.show('subDelete');
    },
    deleteCancel() {
      this.$modal.hide('subDelete');
    },
    deleteSubmit(event) {
      console.log("sub = " + event);
      this.$modal.hide('subDelete');
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