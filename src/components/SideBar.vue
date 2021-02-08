<template>
  <Slide  width="280" right noOverlay>
    <a href="#"><span style="font-weight: 700">분류 전체보기</span></a>
    <a href="#"><span>Main Category</span></a>
    <a class="sub_category" href="#"><span>Sub Category</span></a>
    <a class="sub_category" href="#"><span>Sub Category</span></a>
    <a class="sub_category" href="#"><span>Sub Category</span></a>
    <a href="#"><span>Main Category</span></a>
  </Slide>
</template>

<script>
import { Slide } from 'vue-burger-menu';

export default {
  name: 'SideBar',
  components: {
    Slide
  },
};
</script>

<style lang="scss">
.bm-burger-button {
  width: 18px;
  height: 15px;
  cursor: pointer;
}
.bm-burger-bars {
  background-color: #373a47;
}
.line-style {
  position: absolute;
  height: 20%;
  left: 0;
  right: 0;
}
.cross-style {
  position: absolute;
  top: 12px;
  right: 2px;
  cursor: pointer;
}
.bm-cross {
  background: #555;
}
.bm-cross-button {
  height: 24px;
  width: 24px;
  top: 36px !important;
  right: 22px !important;
}
.bm-menu {
  height: 100%; /* 100% Full-height */
  width: 0; /* 0 width - change this with JavaScript */
  position: fixed; /* Stay in place */
  z-index: 1000; /* Stay on top */
  top: 0;
  left: 0;
  background-color: #cccccc; /* Black*/
  overflow-x: hidden; /* Disable horizontal scroll */
  padding-top: 60px; /* Place content 60px from the top */
  transition: 0.5s; /*0.5 second transition effect to slide in the sidenav*/
}

.bm-overlay {
  background: rgba(0, 0, 0, 0.6);
}
.bm-item-list {
  color: #b8b7ad;
  margin-left: 10%;
  font-size: 20px;
}
.bm-item-list > * {
  font-size: 14px;
  display: flex;
  text-decoration: none;
  padding: 7px 10px;
  font-weight: 400;
  span {
    margin-left: 10px;
    font-weight: 400;
    color: #555;
    &:hover {
      text-decoration: underline;
    }
  }
}

.bm-item-list > .sub_category {
  display: flex;
  text-decoration: none;
  padding: 3px 0 3px 19px;
  span {
    font-size: 13px;
    color: #7d7d7d;
  }
}
</style>