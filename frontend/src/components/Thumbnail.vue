<template>
  <div class="v-uploader">
    <div class="single-upload">
      <div class="image-box">
        <img v-if="!blobUrl" :src="!prevThumbnail ? noImage : prevThumbnail" width="200" height="150">
        <img v-else :src="blobUrl" width="200" height="150">
      </div>
      <div class="btn uploader-button" style="position: relative; overflow: hidden; direction: ltr;">
        <i class="far fa-folder-open"></i>
        <span>select file</span>
        <b-form-file type="file" v-model="$store.state.thumbnailFile" accept="image/*" style="position: absolute; right: 0px; top: 0px; font-family: Arial; font-size: 118px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0; height: 100%;"></b-form-file>
      </div>
    </div>
  </div>

</template>

<script>
import noImage from '@/assets/thumbnail.svg'

export default {
  props: {
    prevThumbnail: String,
  },
  data() {
    return {
      noImage,
      thumbnail: null,
    }
  },
  computed: {
    thumbnailFile() {
      return this.$store.state.thumbnailFile;
    },
    blobUrl() {
      if (!this.thumbnailFile) {
        return null;
      }
      return URL.createObjectURL(this.thumbnailFile);
    }
  }
};
</script>

<style lang="scss" scoped>
.v-uploader {
  .single-upload {
    display: inline-block;
    text-align: center;
    .image-box {
      border-radius: 2px;
      box-shadow: 0 1px 8px #0000001a;
      border: 1px solid #ddd;
      padding: 5px;
      margin-bottom: 10px;
    }
  }
  .uploader-button {
    border-radius: 2px;
    box-shadow: 0 0 2px #0000001f, 0 2px 2px #00000033;
    color: #212121;
    background: #fff;
    border-color: #fff;
    outline: 0;
    width: auto;
    display: inline-block;
    padding: 6px 12px;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857143;
    i {
      font-size: 13px;
      margin-right: 3px;
    }
  }
}
</style>