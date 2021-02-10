<template>
  <aside class="area-aside" :class="{'area-aside-on' : asideOn }" v-click-outside="asideOffEvent">
    <!-- 프로필 -->
    <div class="box-profile" style="background-image: url('#')">
      <div class="inner-box">
        <img src="https://tistory2.daumcdn.net/tistory/2990835/attach/e7621b0d33a046c89b1b45568c87c2f4" class="img-profile" alt="프로필사진">
        <p class="text-profile">문화재청 제휴 콘텐츠로 제작한 Odyssey 스킨 데모 블로그입니다.</p>
        <div  class="box-sns">
          <a href="//www.facebook.com/chloveu" class="link-sns link-facebook">
            <svg xmlns="http://www.w3.org/2000/svg" width="8" height="16" viewBox="0 0 8 16" class="svg">
              <path fill="#B3B3B3" fill-rule="evenodd" d="M8 5v3H5v8H2V8H0V5h2V2a2 2 0 0 1 2-2h4v3H6a1 1 0 0 0-1 1v1h3z"></path>
            </svg>
          </a>
          <a href="//www.youtube.com/user/chluvu" class="link-sns link-youtube">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="12" viewBox="0 0 16 12" class="svg">
              <path fill="#B3B3B3" fill-rule="evenodd" d="M4 0h8a4 4 0 0 1 4 4v4a4 4 0 0 1-4 4H4a4 4 0 0 1-4-4V4a4 4 0 0 1 4-4zm3 4l3 2-3 2V4z"></path>
            </svg>
          </a>
          <a href="//www.instagram.com/chlove_u" class="link-sns link-insta">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" class="svg">
              <path fill="#B3B3B3" fill-rule="evenodd" d="M4 0h8a4 4 0 0 1 4 4v8a4 4 0 0 1-4 4H4a4 4 0 0 1-4-4V4a4 4 0 0 1 4-4zm0 2h8a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2zm4 2a4 4 0 1 1 0 8 4 4 0 0 1 0-8zm0 2a2 2 0 1 1 0 4 2 2 0 0 1 0-4zm4-3a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
            </svg>
          </a>
          <a href="//www.twitter.com/chlove_u" class="link-sns link-twitter">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="13" viewBox="0 0 16 13" class="svg">
              <path fill="#B3B3B3" fill-rule="evenodd" d="M14.476 3.059S14.825 13 5.333 13A10.044 10.044 0 0 1 0 11.471a7.423 7.423 0 0 0 3.81-1.529 3.091 3.091 0 0 1-2.286-1.529h1.524A8.616 8.616 0 0 1 0 4.588h1.524S.45 4.075.762.764a8.438 8.438 0 0 0 6.095 3.059.766.766 0 0 0 .762-.765S7.646 0 11.429 0a4.071 4.071 0 0 1 2.285.765L16 0l-1.524 1.529H16l-1.524 1.53z"></path>
            </svg>
          </a>
        </div>
      </div>
    </div>
    <!-- 카테고리 메뉴 -->
    <div class="box-category box-category-2depth">
      <nav>
        <ul class="tt_category">
          <li>
            <a class="link_tit" href="/">전체 글 보기 <span class="c_cnt">(19)</span></a>
            <ul class="category_list">
              <li>
                <a class="link_item" href="#">
                  고궁의 밤 <span class="c_cnt">(4)</span>
                </a>
              </li>
              <li>
                <a class="link_item" href="#">
                  문화재청 소식 <span class="c_cnt">(7)</span>
                </a>
                <ul class="sub_category_list">
                  <li>
                    <a class="link_sub_item" href="#">
                      정책 소식 <span class="c_cnt">(4)</span>
                    </a>
                  </li>
                  <li>
                    <a class="link_sub_item" href="#">
                      동영상 뉴스 <span class="c_cnt">(3)</span>
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
        </ul>
      </nav>
    </div>
    <!-- 태그 -->
    <div class="box-tag">
      <h3 class="title-sidebar">Tag</h3>
      <div class="box_tag">
        <a href="/tag/#" class="cloud4">ASMR,</a>
        <a href="/tag/#" class="cloud4">유물,</a>
      </div>
    </div>
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
    <!-- 달력 모듈 -->
    <div class="box-calendar">
      <h3 class="title-sidebar"><span class="blind">Calendar</span></h3>
      <div class="inner-calendar">
        <table class="tt-calendar" cellpadding="0" cellspacing="1" style="width: 100%; table-layout: fixed">
          <caption class="cal_month">
            <a href="/archive/202101" title="1개월 앞의 달력을 보여줍니다."></a>
            &nbsp;
            <a href="/archive/202102" title="현재 달의 달력을 보여줍니다.">2021. 02</a>
            &nbsp;
            <a href="/archive/202103" title="1개월 뒤의 달력을 보여줍니다."></a>
          </caption>
          <thead>
          <tr>
            <th class="cal_week2">일</th>
            <th class="cal_week1">월</th>
            <th class="cal_week1">화</th>
            <th class="cal_week1">수</th>
            <th class="cal_week1">목</th>
            <th class="cal_week1">금</th>
            <th class="cal_week1">토</th>
          </tr>
          </thead>
          <tbody>
          <tr class="cal_week">
            <td class="cal_day1">&nbsp;</td>
            <td class=" cal_day cal_day3">1</td>
            <td class=" cal_day cal_day3">2</td>
            <td class=" cal_day cal_day3">3</td>
            <td class=" cal_day cal_day3">4</td>
            <td class=" cal_day cal_day3">5</td>
            <td class=" cal_day cal_day3">6</td>
          </tr>
          <tr class="cal_week cal_current_week">
            <td class=" cal_day cal_day_sunday cal_day3">7</td>
            <td class=" cal_day cal_day3">8</td>
            <td class=" cal_day cal_day4">9</td>
            <td class=" cal_day cal_day3">10</td>
            <td class=" cal_day cal_day3">11</td>
            <td class=" cal_day cal_day3">12</td>
            <td class=" cal_day cal_day3">13</td>
          </tr>
          <tr class="cal_week">
            <td class=" cal_day cal_day_sunday cal_day3">14</td>
            <td class=" cal_day cal_day3">15</td>
            <td class=" cal_day cal_day3">16</td>
            <td class=" cal_day cal_day3">17</td>
            <td class=" cal_day cal_day3">18</td>
            <td class=" cal_day cal_day3">19</td>
            <td class=" cal_day cal_day3">20</td>
          </tr>
          <tr class="cal_week">
            <td class=" cal_day cal_day_sunday cal_day3">21</td>
            <td class=" cal_day cal_day3">22</td>
            <td class=" cal_day cal_day3">23</td>
            <td class=" cal_day cal_day3">24</td>
            <td class=" cal_day cal_day3">25</td>
            <td class=" cal_day cal_day3">26</td>
            <td class=" cal_day cal_day3">27</td>
          </tr>
          <tr class="cal_week">
            <td class=" cal_day cal_day_sunday cal_day3">28</td>
            <td class="cal_day2">&nbsp;</td>
            <td class="cal_day2">&nbsp;</td>
            <td class="cal_day2">&nbsp;</td>
            <td class="cal_day2">&nbsp;</td>
            <td class="cal_day2">&nbsp;</td>
            <td class="cal_day2">&nbsp;</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <!-- 방문자수 -->
    <div class="box-visit">
      <h3 class="title-sidebar"><span class="blind">방문자수</span>Total</h3>
      <p class="text-total">24,757</p>
      <ul>
        <li class="item-visit">Today : 101</li>
        <li class="item-visit">Yesterday : 98</li>
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
export default {
  name: 'Aside',
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