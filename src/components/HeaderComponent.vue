<template>
  <Header :if_var_headersloguntitle="if_var_headersloguntitle" :if_var_headerbannerimage="if_var_headerbannerimage" :if_var_headerbannertitle="if_var_headerbannertitle"
          :if_var_headerbannerlink="if_var_headerbannerlink" :var_headerbannerimage="var_headerbannerimage" :var_headerbannertitlecolor="var_headerbannertitlecolor"
          :var_headerbannerlinkcolor="var_headerbannerlinkcolor" :var_headerbannerlinkovercolor="var_headerbannerlinkovercolor" :var_headerbannerlink="var_headerbannerlink">
    <!-- 블로그 제목 -->
    <template v-slot:title>{{ title }}</template>

    <!-- 슬로건 -->
    <template v-slot:header-slogun-title>{{ var_headersloguntitle }}</template>
    <template v-slot:header-slogun-text><span v-html="var_headersloguntext"></span></template>

    <!-- 카테고리 리스트-->
    <template v-slot:blog-menu>
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
    </template>

    <!-- 배너 제목 -->
    <template v-slot:header-banner-title>{{ var_headerbannertitle }}</template>
  </Header>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'HeaderComponent',
  components: {
    Header,
  },
  data() {
    return {
      // 타이틀
      title: '문화재청',

      // 슬로건
      if_var_headersloguntitle: true,
      var_headersloguntitle: '어제를 담아 내일에 전합니다',
      var_headersloguntext: '과거와 현재, 시간과 사람 사이에 다리를 놓아<br>우리 시대의 문화를 만들어갑니다.',

      // 배너
      if_var_headerbannerimage: true,
      if_var_headerbannertitle: true,
      if_var_headerbannerlink: true,
      var_headerbannerimage: 'https://tistory4.daumcdn.net/tistory/2990835/skinSetting/d1327ac450f449e08fa9b17c5e4545ee',
      var_headerbannertitlecolor: '#ffffff',
      var_headerbannertitle: '우리가 함께해요, "문화재 사랑"',
      var_headerbannerlinkcolor: '#ffffff',
      var_headerbannerlinkovercolor: '#D7E5FF',
      var_headerbannerlink: '//www.cha.go.kr/main.html',
    }
  }
};
</script>

<style scoped>

</style>