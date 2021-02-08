<template>
  <aside class="area-aside" :class="{'area-aside-on' : asideOn }">
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
            <a class="link_tit" href="#">전체 글 보기 <span class="c_cnt">(19)</span></a>
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

  </aside>
</template>

<script>
export default {
  name: 'Aside',
  computed: {
    asideOn() {
      return this.$store.state.asideOn;
    }
  }
};
</script>

<style scoped>

</style>