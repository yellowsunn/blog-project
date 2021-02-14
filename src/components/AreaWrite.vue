<template>
  <form method="post">
    <div class="area-write">
      <div class="box-account">
        <input type="text" title="이름" placeholder="이름" :value="data.user" @input="data.user = $event.target.value"/>
        <input type="password" title="비밀번호" maxlength="12" placeholder="비밀번호" v-model="data.password"/>
      </div>
      <div class="box-textarea">
        <textarea placeholder="내용을 입력해주세요." :value="data.desc" @input="data.desc = $event.target.value"></textarea>
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
  data() {
    return {
      data: {
        user: '',
        password: '',
        desc: '',
      }
    }
  },
  methods: {
    submitEvent() {
      this.$emit('submit', this.data);
    }
  }
};
</script>

<style scoped>

</style>