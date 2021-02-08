<template>
  <header class="header">
    <div class="line-bottom display-none"></div>

    <!-- inner-header -->
    <div class="inner-header slogun-use topnavmenu">

      <div class="box-header">
        <h1 class="title-logo">
          <a href="/" title="[##_title_##]" class="link_logo">
            문화재청
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
        <div class="area-slogun topnavmenu slogunmobileoff">
          <strong>어제를 담아 내일에 전합니다</strong>
          <p>과거와 현재, 시간과 사람 사이에 다리를 놓아<br>우리 시대의 문화를 만들어갑니다.</p>
        </div>

        <!-- area-gnb -->
        <div class="area-gnb">
          <nav class="topnavmenu">
            <ul>
              <li class="t_menu_home first">
                <a href="/">홈</a>
              </li>
              <li class="t_menu_category">
                <a href="#">고궁의 밤</a>
              </li>
              <li class="t_menu_category">
                <a href="#">행사 안내</a>
              </li>
              <li class="t_menu_guestbook">
                <a href="#">방명록</a>
              </li>
              <li class="t_menu_link_1 last">
                <a href="#">문화재청 홈페이지</a>
              </li>
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
};
</script>

<style lang="scss" scoped>
</style>