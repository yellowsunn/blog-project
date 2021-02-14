<template>
  <header class="header">
    <div class="line-bottom display-none"></div>

    <!-- inner-header -->
    <div class="inner-header topnavmenu" :class="{ 'slogun-use' : if_var_headersloguntitle}">

      <div class="box-header">
        <h1 class="title-logo">
          <a href="/" title="title" class="link_logo">
            <slot name="title"></slot>
          </a>
        </h1>

        <!-- search-bar for PC -->
        <div class="util use-top">
          <div class="search">
            <input class="searchInput" type="text" name="search" value="" placeholder="Search..." onkeypress="if (event.keyCode == 13) { requestSearch('.util.use-top .searchInput') }"/>
          </div>
        </div>
      </div>

      <!-- area-align -->
      <div class="area-align">

        <template v-if="if_var_headersloguntitle">
          <!-- area-slogan -->
          <div class="area-slogun topnavmenu slogunmobileoff">
            <strong><slot name="header-slogun-title"></slot></strong>
            <p><slot name="header-slogun-text"></slot></p>
          </div>
        </template>

        <!-- area-gnb -->
        <div class="area-gnb">
          <nav class="topnavmenu">
            <ul>
              <slot name="blog-menu"></slot>
            </ul>
          </nav>
        </div>

        <button type="button" class="button-menu" @click="asideOnEvent">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="14" viewBox="0 0 20 14">
            <path fill="#333" fill-rule="evenodd" d="M0 0h20v2H0V0zm0 6h20v2H0V6zm0 6h20v2H0v-2z"></path>
          </svg>
        </button>

        <template v-if="if_var_headerbannerimage">
          <!-- area-banner -->
          <div class="area-promotion height400 bannermobile-on" :style="{ backgroundImage: `url(${var_headerbannerimage})`}">
            <div class="inner-promotion">
              <div class="box-promotion">
                <template v-if="if_var_headerbannertitle">
                  <strong :style="{ color: var_headerbannertitlecolor }"><slot name="header-banner-title"></slot></strong>
                </template>

                <template v-if="if_var_headerbannerlink">
                  <a :href="var_headerbannerlink" class="link-promotion" ref="linkPromotion" @mouseover="headerbannerlinkHover" @mouseout="headerbannerlinkOut">자세히보기</a>
                </template>
              </div>
            </div>
          </div>
        </template>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  props: {
    if_var_headersloguntitle: Boolean,
    if_var_headerbannerimage: Boolean,
    if_var_headerbannertitle: Boolean,
    if_var_headerbannerlink: Boolean,
    var_headerbannerimage: String,
    var_headerbannertitlecolor: String,
    var_headerbannerlinkcolor: String,
    var_headerbannerlinkovercolor: String,
    var_headerbannerlink: String,
  },
  mounted() {
    this.$refs.linkPromotion.style.background = this.var_headerbannerlinkcolor;
  },
  methods: {
    headerbannerlinkHover() {
      this.$refs.linkPromotion.style.background = this.var_headerbannerlinkovercolor;
    },
    headerbannerlinkOut() {
      this.$refs.linkPromotion.style.background = this.var_headerbannerlinkcolor;
    },
    asideOnEvent(event) {
      this.$store.state.asideOn = true;
      document.body.classList.add('bg-dimmed');
      document.body.style.overflow = 'hidden';
      event.stopImmediatePropagation();
    }
  }
};
</script>

<style lang="scss" scoped>
</style>