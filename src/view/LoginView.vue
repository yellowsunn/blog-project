<template>
  <div class="login_container">
    <b-form class="login_form" @submit="onSubmit">
      <h2>Sign in</h2>
      <b-form-input class="login_input" v-model="account.username" placeholder="Username" size="lg" autocomplete="username" required></b-form-input>
      <b-form-input class="login_input" v-model="account.password" type="password" placeholder="Password" autocomplete="current-password" size="lg" required></b-form-input>
      <b-button class="submit" type="submit" variant="primary">Sign In</b-button>
      <b-alert class="login_error" v-if="isError" show variant="danger" dismissible>{{ errorMsg }}</b-alert>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      account: {
        username: "",
        password: "",
      },
      isError: false,
      errorMsg: "",
    }
  },
  methods: {
    async onSubmit(event) {
      event.preventDefault();
      try {
        await this.$store.dispatch('FETCH_LOGIN', this.account);
        this.isError = false;

        const url = `${window.location.protocol}//${window.location.host}`;
        window.location.href = `${url}`;

      } catch (error) {
        this.isError = true;
        if (error.response.status === 401) {
          this.errorMsg = "아이디와 비밀번호가 일치하지 않습니다.";
        } else {
          this.errorMsg = "로그인에 실패했습니다. 다시 시도해주세요.";
        }
      }
    }
  }
};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css?family=Roboto:400,500,700,400italic|Material+Icons');

.login_container {
  font-family: 'Roboto', sans-serif;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;

  .login_form {
    width: 28rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 2.5rem;
    border-radius: 8px;
    -webkit-border-radius: 8px;
    border: 1px solid #dadce0;

    h2 {
      font-size: 24px;
      font-weight: 400;
      margin-bottom: 2.5rem;
    }

    .login_input {
      margin-bottom: 1.875rem;
    }

    .submit {
      align-self: flex-end;
    }

    .login_error {
      margin-top: 1.875rem;
      width: 100%;
    }
  }
}

@media screen and (max-width: 601px) {
  .login_container {
    display: flex;
    align-items: flex-start;
    padding: 60px 40px 36px;
    .login_form {
      border: transparent;
      padding: 0;
      width: 100%;
    }
  }
}

@media screen and (max-width: 450px) {
  .login_container {
    padding: 40px 24px 36px;
  }
}
</style>