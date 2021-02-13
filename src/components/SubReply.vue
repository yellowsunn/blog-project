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
      <div class="box-modify">
        <button type="button" class="button-modify" @click="modifyToggle"><span class="blind">더보기</span></button>
        <ul class="list-modify" ref="listModify">
          <li><a href="#">수정/삭제</a></li>
        </ul>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  data() {
    return {
      isModified: false
    }
  },
  methods: {
    modifyToggle() {
      this.isModified = !this.isModified;
      if (this.isModified) {
        this.$refs.listModify.style.display = 'block';
      } else {
        this.$refs.listModify.style.display = 'none';
      }
    }
  }
};
</script>

<style scoped>
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