import Vue from 'vue';
import Vuex from 'vuex';
import {
  getArticleData,
  getCategoryData,
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
  },
  actions : {
    async GET_HEADER_DATA({ commit }) {
      const response = await getHeaderData();
      try {
        commit('GET_HEADER_DATA', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async GET_MAIN_PAGE_DATA({ commit }) {
      const response = await getMainPageData();
      try {
        commit('GET_COVER_ARTICLE_DATA', response.data.cover);
        commit('GET_COVER_CATEGORY_DATA', response.data.category);
      } catch (error) {
        console.log(error);
      }
    },
    async GET_CATEGORY_DATA({ commit }, { categoryId, page }) {
      const response = await getCategoryData(categoryId, page);
      try {
        commit('GET_CATEGORY_DATA', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async GET_ARTICLE_DATA({ commit }, articleId) {
      const response = await getArticleData(articleId);
      try {
        commit('GET_ARTICLE_DATA', response.data);
      } catch (error) {
        console.log(error);
      }
    }
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
    }
  }
});