import Vue from 'vue';
import Vuex from 'vuex';
import {
  getArticleData,
  getCategoryData, getCommentCount, getCommentData,
  getHeaderData,
  getMainPageData,
} from '@/api';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    // aside toggle
    asideOn: false,
    // 메인 페이지, 카테고리 페이지인지 확인
    isMainPage: false,
    isCategoryPage: false,
    isViewPage: false,
    // 댓글이 로딩되고 있을때 true
    loadComments: false,

    coverHeaderData: {},
    // 메인 페이지에 뜨는 커버 게시글
    coverArticle: {},
    // 메인 페이지에 뜨는 카테고리 게시글 목록
    coverCategoryData: {},
    // 위에랑 통합할 수 있을듯
    categoryData: {},
    articleData: {},
    commentData: {
      total: 3,
      item: [
        {
          name: '닉네임1',
          date: '2021.02.13 23:40',
          desc: '예제 텍스트입니다.<br> 예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>',
          reply: [
            {
              name: '닉네임2',
              date: '2021.02.13 23:40',
              desc: '예제 텍스트입니다.<br> 예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>',
            },
            {
              name: '닉네임2',
              date: '2021.02.13 23:40',
              desc: '예제 텍스트입니다.<br> 예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>',
            },
          ]
        },
        {
          name: '닉네임3',
          date: '2021.02.13 23:40',
          desc: '예제 텍스트입니다.<br> 예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>예제 텍스트입니다.<br>',
        }
      ]
    }
  },
  getters: {
    isCommentFirst(state) {
      return state.commentData.first;
    }
  },
  actions : {
    async GET_HEADER_DATA({ commit }) {
      try {
        const response = await getHeaderData();
        commit('GET_HEADER_DATA', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async GET_MAIN_PAGE_DATA({ commit }) {
      try {
        const response = await getMainPageData();
        commit('GET_COVER_ARTICLE_DATA', response.data.cover);
        commit('GET_COVER_CATEGORY_DATA', response.data.category);
      } catch (error) {
        console.log(error);
      }
    },
    async GET_CATEGORY_DATA({ commit }, { categoryId, page }) {
      try {
        const response = await getCategoryData(categoryId, page);
        commit('GET_CATEGORY_DATA', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async GET_ARTICLE_DATA({ commit }, articleId) {
      try {
        const response = await getArticleData(articleId);
        commit('GET_ARTICLE_DATA', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async GET_COMMENT_DATA({ commit }, { articleId, page }) {
      try {
        if (page === undefined) {
          const commentCount = await getCommentCount(articleId);
          page = parseInt((commentCount.data - 1) / 30);
          const response = await getCommentData(articleId, page);
          commit('GET_COMMENT_DATA', response.data);
        } else {
          // 댓글 더보기
          commit('LOADING_COMMENT_STATE');

          const response = await getCommentData(articleId, page);
          commit('UPDATE_COMMENT_DATA', response.data);

          commit('INIT_COMMENT_STATE');
        }
      } catch (error) {
        console.log(error);
        commit('INIT_COMMENT_STATE')
      }
    },
  },
  mutations: {
    GET_HEADER_DATA(state, data) {
      state.coverHeaderData = data;
    },
    GET_COVER_ARTICLE_DATA(state, data) {
      state.coverArticle = data;
    },
    GET_COVER_CATEGORY_DATA(state, data) {
      state.coverCategoryData = data;
    },
    GET_CATEGORY_DATA(state, data) {
      state.categoryData = data;
    },
    GET_ARTICLE_DATA(state, data) {
      state.articleData = data;
    },
    GET_COMMENT_DATA(state, data) {
      state.commentData = data;
      state.commentData.content = state.commentData.content.filter((comment) => {
        return !comment.parentCommentId
      });
    },
    UPDATE_COMMENT_DATA(state, data) {
      // 기존 콘텐츠
      const content = state.commentData.content;
      let newContent = data.content.filter((comment) => {
        return !comment.parentCommentId;
      });
      newContent.push(...content);

      // 중복 제거
      let set = new Set(newContent.map(JSON.stringify));
      newContent = Array.from(set).map(JSON.parse);

      state.commentData = data;
      state.commentData.content = newContent;
    },
    LOADING_COMMENT_STATE(state) {
      state.loadComments = true;
    },
    INIT_COMMENT_STATE(state) {
      state.loadComments = false;
    }
  }
});