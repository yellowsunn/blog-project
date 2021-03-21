<template>
  <section>
    <div class="main_container">
      <div class="sub_container">
        <span class="label">타이틀: </span><b-form-input class="header_input" v-model="header.title"></b-form-input>
      </div>
      <div class="sub_container">
        <span class="label">슬로건 타이틀: </span><b-form-input class="header_input" v-model="header.slogunTitle"></b-form-input>
      </div>
      <div class="sub_container">
        <span class="label">슬로건 상세문구: </span><b-form-textarea class="header_input text_area" v-model="header.slogunText"></b-form-textarea>
      </div>
      <div class="btn_container">
        <b-button class="button" variant="primary" @click="change">변경</b-button>
        <b-button class="button" variant="danger" @click="cancel">취소</b-button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  async created() {
    await this.$store.dispatch('GET_HEADER_DATA');
    this.header = this.$store.state.coverHeaderData;
  },
  data() {
    return {
      header: {
        title: '',
        slogunTitle: '',
        slogunText: '',
      }
    }
  },
  methods: {
    async change() {
      try {
        await this.$store.dispatch('UPDATE_HEADER_DATA', this.header);
        await this.$router.push("/manage");
      } catch (error) {
        if (error.response.status === 401) {
          alert("블로그 관리자만 변경할 수 있습니다.");
          await this.$router.push('/login');
        } else {
          alert("예기치 못한 오류가 발생했습니다.");
        }
      }
    },
    cancel() {
      this.$router.push("/manage");
    }
  }
};
</script>

<style lang="scss" scoped>
section {
  display: flex;
  width: 100vw;
  height: 100vh;
  justify-content: center;
  align-items: center;
  .main_container {
    display: flex;
    flex-direction: column;
    padding: 1.875em;
    border: 1px solid;
    border-radius: 0.500em;
    .sub_container {
      display: flex;
      align-items: center;
      padding: 0.625em;
      .label {
        width: 9.375em;
      }
      .header_input {
        width: 31.250em;
      }
      .text_area {
        height: 7.5em;
      }
    }
    .btn_container {
      align-self: flex-end;
      .button {
        width: 6.250em;
        margin: 0.625em;
      }
    }
  }
}

@media screen and (max-width: 1060px) {
  section {
    .main_container .sub_container {
      .header_input {
        width: 21.875em;
      }
    }
  }
}

@media screen and (max-width: 660px) {
  section {
    font-size: 14px;
    .main_container {
      border: none;
      .sub_container {
        flex-direction: column;
        padding: 1em 0.625em;
        .label {
          align-self: flex-start;
          margin-bottom: 0.214em;
        }
        .header_input {
          width: 18.750em;
        }
      }
    }
  }
}
</style>