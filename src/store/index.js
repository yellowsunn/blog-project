import Vue from 'vue';
import Vuex from 'vuex';
import actions from '@/store/actions';
import mutations from '@/store/mutations';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    isAuthorized: false,
    // aside toggle
    asideOn: false,
    // 메인 페이지, 카테고리 페이지인지 확인
    isMainPage: false,
    isCategoryPage: false,
    isViewPage: false,
    // 댓글이 로딩되고 있을때 true
    loadComments: false,
    // 페이지가 로딩되고 있을때 true
    loadPage: false,

    // 답글을 다는 경우에 사용되는 Id
    parentCommentId: null,
    // 삭제하고자 하는 댓글 Id
    deleteCommentId: null,
    // 섬네일 이미지 파일
    thumbnailFile: null,

    coverHeaderData: {},
    // 메인 페이지에 뜨는 커버 게시글
    coverArticle: {},
    // 메인 페이지에 뜨는 카테고리 게시글 목록
    coverCategoryData: {},
    categoryData: {},
    articleData: {},
    commentData: {},
    asideCategoryList: {},
    asideProfileData: {},
    asideArticles: {},
    coverCategoryId: {},
    categoryInfo: {}
  },
  getters: {
    isCommentFirst(state) {
      return state.commentData.first;
    }
  },
  actions,
  mutations,
});