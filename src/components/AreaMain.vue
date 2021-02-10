<template>
  <!-- area-main -->
  <div class="area-main">
    <!-- s_list / 카테고리, 검색 리스트 -->
    <Category :categoryData="categoryData" v-if="isCategoryPage"></Category>

    <!-- area-view / 뷰페이지 및 기본 영역 -->
    <div class="area-view" v-if="isViewPage">
      <!-- 이미지가 없는 경우 article-header-noimg -->
      <div class="article-header" thumbnail="https://img1.daumcdn.net/thumb/R1440x0/?scode=mtistory2&amp;fname=https://blog.kakaocdn.net/dn/lNnI9/btqHJj5an3X/An2u3X2N9fSBRQTak6e450/img.jpg" style="background-image: url(&quot;https://img1.daumcdn.net/thumb/R1440x0/?scode=mtistory2&amp;fname=https://blog.kakaocdn.net/dn/lNnI9/btqHJj5an3X/An2u3X2N9fSBRQTak6e450/img.jpg&quot;);">
        <div class="inner-header">
          <div class="box-meta">
            <p class="category">고궁의 밤</p>
            <h2 class="title-article">고궁의 밤 - 경복궁</h2>
            <div class="box-info">
              <span class="writer">Skin Demo 6 </span>
              <span class="date">2020. 9. 1. 13:41</span>
            </div>
          </div>
        </div>
      </div>

      <!-- 에디터 영역 -->
      <div class="article-view">
        <p>
          코로나 19로 조선 4대 고궁의 문은 닫히고, <br>
          계속되던 야간관람과 야간행사도 <br>
          잠시 멈춰 있습니다. <br>
          <br>
          못 보니까 더 아쉬운 고궁의 밤, <br>
          이제서야 소중함을 느끼게 됩니다. <br>
          조만간 다시 만날 거예요. <br>
          <br>
          그때까지 잊지 마시라고 <br>
          경복궁, 창덕궁, 덕수궁, 창경궁의 <br>
          아름다운 밤을 공개합니다. <br>
          다시 만나면 더 아껴주세요. <br>
          우리 고궁의 밤을.
        </p>

        <!-- 공감 아이콘 -->
        <div class="container_postbtn #post_button_group">
          <div class="postbtn_like">
            <div class="wrap_btn" id="reaction-68">
              <button class="btn_post uoc-icon">
                <!-- 클래스 추가: empathy_up_without_ani like_on -->
                <div class="uoc-icon">
                  <span class="ico_postbtn ico_like">좋아요</span>
                  <span class="txt_like uoc-count">공감</span>
                </div>
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- article-footer -->
      <div class="article-footer">
        <!-- article-tag -->
        <div class="article-tag">
          <h3 class="title-footer">Tag</h3>
          <div class="box-tag">
            <a href="/tag/%EA%B2%BD%EB%B3%B5%EA%B6%81" rel="tag">경복궁</a>,
            <a href="/tag/%EA%B3%A0%EA%B6%81" rel="tag">고궁</a>,
            <a href="/tag/%EA%B3%A0%EA%B6%81%EC%9D%98%20%EB%B0%A4" rel="tag">고궁의 밤</a>,
            <a href="/tag/%EB%8B%A4%EC%9D%8C%20%EA%B0%A4%EB%9F%AC%EB%A6%AC" rel="tag">다음 갤러리</a>
          </div>
        </div>
        <!-- article-page -->
        <div class="article-page">
          <h3 class="title-footer">'고궁의 밤'의 다른글</h3>
          <ul>
            <li>
              <a href="/67"><span>이전글</span><strong>고궁의 밤 - 창덕궁</strong></a>
            </li>
            <li><span>현재글</span><strong>고궁의 밤 - 경복궁</strong></li>
            <li><span>다음글</span><strong>고궁의 밤 - XX궁</strong></li>
          </ul>
        </div>
        <!-- article-reply -->
        <div class="article-reply">
          <div class="box-total">
            <a href="#">댓글 <span>0</span></a>
          </div>
          <div>
            <!-- area-reply -->
            <div class="area-reply"></div>
            <form method="post">
              <!-- reply_write -->
              <div class="area-write">
                <div class="box-account">
                  <input type="text" title="이름" placeholder="이름" />
                  <input type="password" title="비밀번호" maxlength="12" placeholder="비밀번호" />
                </div>
                <div class="box-textarea">
                  <textarea placeholder="내용을 입력해주세요."></textarea>
                </div>
                <div class="box-write">
                  <label class="xe-label">
                    <input type="checkbox" id="secret">
                    <span class="xe-input-helper"></span>
                    <span class="xe-label-text">비밀글</span>
                  </label>
                  <button type="button" class="btn_register">등록</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>

      <AreaView></AreaView>
    </div>

    <!-- area-common -->
    <div class="area-common" v-if="isMainPage">
      <!-- 커버에 띄울 게시글 영역 -->
      <AreaCover :data="coverData"></AreaCover>

      <!-- 커버에 띄울 카테고리 목록 영역 -->
      <AreaCoverCategory :categoryData="coverCategoryData"></AreaCoverCategory>
    </div>

    <!-- 페이지 영역 -->
    <AreaView v-if="isCategoryPage"></AreaView>
  </div>
</template>

<script>
import AreaCover from '@/components/AreaCover';
import AreaCoverCategory from '@/components/AreaCoverCategory';
import AreaView from '@/components/AreaView';
import Category from '@/components/Category';

export default {
  components: {
    AreaCover, AreaCoverCategory,
    AreaView, Category,
  },
  computed: {
    isMainPage() {
      return this.$store.state.isMainPage;
    },
    isCategoryPage() {
      return this.$store.state.isCategoryPage;
    },
    isViewPage() {
      return this.$store.state.isViewPage;
    },
    coverData() {
      return this.$store.state.coverData;
    },
    coverCategoryData() {
      return this.$store.state.coverCategoryData;
    },
    categoryData() {
      return this.$store.state.categoryData;
    }
  },
  props: {
    cover_item_url: String,
    cover_item_thumbnail: String,
  },
};
</script>

<style scoped>
/*.archives::before {*/
/*  content: "'";*/
/*}*/
/*.archives::after {*/
/*  content: "'의 검색결과";*/
/*}*/
</style>