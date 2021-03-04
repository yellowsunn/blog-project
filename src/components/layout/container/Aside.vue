<template>
  <aside class="area-aside" :class="{'area-aside-on' : asideOn }" v-click-outside="asideOffEvent">
    <!-- 프로필 -->
    <AsideProfile></AsideProfile>

    <!-- 카테고리 메뉴 -->
    <AsideCategoryList></AsideCategoryList>
    <!-- 최근글과 인기글 -->
    <div class="box-recent">
      <h3 class="title-sidebar blind">최근글과 인기글</h3>
      <ul class="tab-recent">
        <li class="tab-button recent_button on"><a class="tab-button" href="#" onclick="return false;">최근글</a></li>
        <li class="tab-button sidebar_button"><a class="tab-button" href="#" onclick="return false;">인기글</a></li>
      </ul>
      <ul class="list-recent" style="display: block;">
        <li>
          <a href="/68" class="link-recent">
            <p class="thumbnail" style="background-image:url('https://img1.daumcdn.net/thumb/R750x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlNnI9%2FbtqHJj5an3X%2FAn2u3X2N9fSBRQTak6e450%2Fimg.jpg')"></p>
            <div class="box-recent">
              <strong>고궁의 밤 - 경복궁</strong>
              <span>2020.09.01 13:41</span>
            </div>
          </a>
        </li>
        <li>
          <a href="/67" class="link-recent">
            <p class="thumbnail" style="background-image:url('https://img1.daumcdn.net/thumb/R750x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcLP4bH%2FbtqHy6e2szz%2F1ZvZwYdtnCOImbsH6BPKOk%2Fimg.jpg')"></p>
            <div class="box-recent">
              <strong>고궁의 밤 - 창덕궁</strong>
              <span>2020.09.01 13:37</span>
            </div>
          </a>
        </li>
        <li>
          <a href="/66" class="link-recent">
            <p class="thumbnail" style="background-image:url('https://img1.daumcdn.net/thumb/R750x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fc6EMkq%2FbtqHJjjRshA%2FKzOpjx6QkTPVfOAFJekZgk%2Fimg.jpg')"></p>
            <div class="box-recent">
              <strong>고궁의 밤 - 덕수궁</strong>
              <span>2020.09.01 13:33</span>
            </div>
          </a>
        </li>
      </ul>
      <ul class="list-recent list-tab" style="display: none;">
        <li>
          <a href="/68" class="link-recent">
            <p class="thumbnail" style="background-image:url('https://img1.daumcdn.net/thumb/R750x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlNnI9%2FbtqHJj5an3X%2FAn2u3X2N9fSBRQTak6e450%2Fimg.jpg')"></p>
            <div class="box-recent">
              <strong>고궁의 밤 - 경복궁</strong>
              <span>2020.09.01 13:41</span>
            </div>
          </a>
        </li>
        <li>
          <a href="/63" class="link-recent">
            <p class="thumbnail" style="background-image:url('https://img1.daumcdn.net/thumb/R750x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2ForaF1%2FbtqHAmWduTn%2F9oCrT8yOPrsktnRFKqXpKk%2Fimg.jpg')"></p>
            <div class="box-recent">
              <strong>세계에 활짝 열린 실감형 궁궐체험 앱 ‘창덕궁’</strong>
              <span>2020.09.01 11:56</span>
            </div>
          </a>
        </li>
        <li>
          <a href="/62" class="link-recent">
            <p class="thumbnail" style="background-image:url('https://img1.daumcdn.net/thumb/R750x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F0RbB7%2FbtqHLYzt2bY%2FL5z8mEJ6T1Wl9QE6d1iQvK%2Fimg.jpg')"></p>
            <div class="box-recent">
              <strong>「정선 정암사 수마노탑」은 국보,「안동 봉황사 대웅전」은 보물로 지정</strong>
              <span>2020.09.01 11:54</span>
            </div>
          </a>
        </li>
      </ul>
    </div>

    <!-- search-bar for MOBILE -->
    <div class="util use-sidebar">
      <div class="search">
        <input class="searchInput" type="text" name="search" value="" placeholder="검색내용을 입력하세요." onkeypress="if (event.keyCode == 13) { requestSearch('.util.use-sidebar .searchInput') }">
      </div>
    </div>
  </aside>
</template>

<script>
import AsideProfile from '@/components/layout/container/aside/AsideProfile'
import AsideCategoryList from '@/components/layout/container/aside/AsideCategoryList'

export default {
  components: {
    AsideProfile,
    AsideCategoryList,
  },
  computed: {
    asideOn() {
      return this.$store.state.asideOn;
    }
  },
  methods: {
    asideOffEvent() {
      if (this.$store.state.asideOn === true) {
        this.$store.state.asideOn = false;
        document.body.classList.remove('bg-dimmed');
        document.body.style.overflow = '';
      }
    }
  }
};
</script>

<style scoped>

</style>