<template>
  <Header v-bind:hasSlogun="hasSlogun" v-bind:hasGnb="hasGnb">
    <!-- 블로그 제목 -->
    <template v-slot:title>문화재청</template>

    <!-- 슬로건 -->
    <template v-slot:header-slogun-title>어제를 담아 내일에 전합니다</template>
    <template v-slot:header-slogun-text>과거와 현재, 시간과 사람 사이에 다리를 놓아<br>우리 시대의 문화를 만들어갑니다.</template>

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
      hasSlogun: true,
      hasGnb: true,
    }
  }
};
</script>

<style scoped>

</style>