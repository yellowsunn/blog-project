<template>
  <header class="header">
    <div class="line-bottom display-none"></div>

    <!-- inner-header -->
    <div class="inner-header slogun-use topnavmenu">

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
        <!-- area-slogan -->
        <div class="area-slogun topnavmenu slogunmobileoff" v-if="hasSlogun">
          <strong><slot name="header-slogun-title"></slot></strong>
          <p><slot name="header-slogun-text"></slot></p>
        </div>

        <!-- area-gnb -->
        <div class="area-gnb" v-if="hasGnb">
          <nav class="topnavmenu">
            <ul>
              <slot name="blog-menu"></slot>
            </ul>
          </nav>
        </div>

        <button type="button" class="button-menu">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="14" viewBox="0 0 20 14">
            <path fill="#333" fill-rule="evenodd" d="M0 0h20v2H0V0zm0 6h20v2H0V6zm0 6h20v2H0v-2z"></path>
          </svg>
        </button>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  props: {
    hasSlogun: Boolean,
    hasGnb: Boolean
  }
};
</script>

<style lang="scss" scoped>
</style>