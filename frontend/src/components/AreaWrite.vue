<template>
  <form method="post">
    <div class="area-write">
      <div class="box-account" v-if="!isAuthorized">
        <input type="text" title="이름" maxlength="30" placeholder="이름" :value="comment.name" @input="comment.name = $event.target.value" autocomplete="off"/>
        <input type="password" title="비밀번호" maxlength="20" placeholder="비밀번호" autocomplete="off" v-model="comment.password"/>
      </div>
      <div class="box-textarea">
        <textarea placeholder="내용을 입력해주세요." maxlength="1000" :value="comment.content" @input="comment.content = $event.target.value"></textarea>
      </div>
      <div class="box-write">
        <label class="xe-label"></label>
        <button type="button" class="btn_register" @click="submitEvent">등록</button>
      </div>
    </div>
  </form>
</template>

<script>
export default {
  computed: {
    isAuthorized() {
      return this.$store.state.isAuthorized;
    }
  },
  data() {
    return {
      comment: {
        name: '',
        password: '',
        content: '',
      }
    }
  },
  methods: {
    submitEvent() {
      if (!this.isAuthorized) {
        if (!this.comment.password) {
          alert('비밀번호를 입력해 주세요.');
          return;
        }
        if (!this.comment.name) {
          alert('닉네임을 입력해 주세요.');
          return;
        }
      }
      if (!this.comment.content) {
        alert('내용을 입력해 주세요.'); return;
      }

      this.$emit('submitData', this.comment);

      // 초기화
      this.comment = { name: '', password: '', content: '' }
    }
  }
};
</script>

<style scoped>

</style>