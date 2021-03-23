<template>
  <section>
    <div class="main_container">
      <div class="sub_container">
        <span class="label label_profile">프로필: </span>
        <Thumbnail :prevThumbnail="profile.profileImage"></Thumbnail>
      </div>
      <div class="sub_container">
        <span class="label">프로필 문구: </span><b-form-input class="header_input" v-model="profile.profileText"></b-form-input>
      </div>
      <div class="btn_container">
        <b-button class="button" variant="primary" @click="submit">변경</b-button>
        <b-button class="button" variant="danger" @click="cancel">취소</b-button>
      </div>
    </div>
  </section>
</template>

<script>
import Thumbnail from '@/components/Thumbnail';
import uuidGenerator from '@/common/uuidGenerator';
export default {
  components: { Thumbnail },
  async created() {
    await this.$store.dispatch('GET_ASIDE_PROFILE_DATA');
    this.profile = this.$store.state.asideProfileData;
  },
  computed: {
    thumbnailFile() {
      return this.$store.state.thumbnailFile;
    }
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
    async submit() {
      const formData = new FormData();
      if (this.thumbnailFile) {
        const profile = uuidGenerator() + this.getImageType(this.thumbnailFile.name);
        formData.append("profileFile", this.thumbnailFile, profile);
      }
      formData.append("profileText", this.profile.profileText);

      try {
        await this.$store.dispatch('UPDATE_ASIDE_PROFILE_DATA', formData);
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
    },
    getImageType(imageName) {
      const regex = /(.+)(\.\w+)/;
      const result = imageName.match(regex);
      return result[2];
    },
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