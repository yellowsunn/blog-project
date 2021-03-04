<template>
  <div class="box-profile" style="background-image: url('#')">
    <div class="inner-box">
      <img :src="!profileImage ? noImage : profileImage" class="img-profile" alt="프로필사진">
      <p class="text-profile">{{ data.profileText }}</p>

      <div class="box_sns">
        <a href="https://github.com/yellowsunn" class="link_github"><i class="fab fa-github"></i></a>
      </div>
    </div>
  </div>
</template>

<script>
import noImage from '@/assets/profile.png';

export default {
  created() {
    this.$store.dispatch('GET_ASIDE_PROFILE_DATA');
  },
  computed: {
    data() {
      return this.$store.state.asideProfileData;
    },
    profileImage() {
      return this.data.profileImage;
    }
  },
  data() {
    return {
      noImage
    }
  }
};
</script>

<style lang="scss" scoped>
.link_github {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 34px;
  height: 34px;
  border: 1px solid #ddd;
  border-radius: 34px;
  margin: 0 auto;
  color: #b2b2b2;
  text-decoration: none;
}

.box_sns {
  text-align: center;
  margin-bottom: 45px;
}

@media screen and (max-width: 1060px) {
  .link_github {
    width: 30px;
    height: 30px;
  }
}
</style>