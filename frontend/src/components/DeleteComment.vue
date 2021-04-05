<template>
  <div>
    <header>
      <h1 class="header_title">댓글 삭제</h1>
    </header>
    <form class="comment_form">
      <fieldset>
        <div class="wrap_password">
          <p class="password_message">댓글 작성 시 입력한 비밀번호를 넣어주세요</p>
          <label><input type="password" id="password" class="input confirm_password" autocomplete="off" v-model="password" style="background-repeat: no-repeat; background-attachment: scroll; background-size: 16px 18px; background-position: 98% 50%; cursor: auto;"></label>
        </div>
        <div class="wrap_btn wrap_btn_confirm">
          <button type="reset" class="btn btn_reset" @click="cancelEvent">취소</button>
          <button type="button" class="btn btn_submit" :disabled="isEmpty" @click="submitEvent">확인</button>
        </div>
      </fieldset>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      password: ''
    }
  },
  computed: {
    isEmpty() {
      return this.password === '' || this.password === undefined;
    },
  },
  methods: {
    cancelEvent() {
      this.$emit('cancel');
    },
    submitEvent() {
      this.$emit('delete', this.password);
    }
  }
};
</script>

<style scoped src="../css/popup-comment.css">
</style>