<template>
  <section>
    <div class="main_container">
      <div class="sub_container">
        <span class="label label_profile">프로필 </span>
        <Thumbnail :prevThumbnail="profile.profileImage"></Thumbnail>
      </div>
      <div class="sub_container">
        <span class="label">프로필 문구: </span><b-form-input class="header_input" v-model="profile.profileText"></b-form-input>
      </div>
      <div class="btn_container">
        <b-button class="button" variant="primary">변경</b-button>
        <b-button class="button" variant="danger" @click="cancel">취소</b-button>
      </div>
    </div>
  </section>
</template>

<script>
import Thumbnail from '@/components/Thumbnail';
export default {
  components: { Thumbnail },
  async created() {
    await this.$store.dispatch('GET_ASIDE_PROFILE_DATA');
    this.profile = this.$store.state.asideProfileData;
  },
  data() {
    return {
      profile: {
        profileImage: '',
        profileText: '',
      }
    }
  },
  methods: {
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
        padding: 1em 0.625em;;
        .label {
          align-self: flex-start;
          margin-bottom: 0.214em;
        }
        .label_profile {
          margin-left: 3.429em;
        }
        .header_input {
          width: 18.750em;
        }
      }
    }
  }
}
</style>