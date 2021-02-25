import Vue from 'vue';
import Vuex from 'vuex';
import { getCategoryData, getHeaderData, getMainPageData } from '@/api';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    // aside toggle
    asideOn: false,
    // 메인 페이지, 카테고리 페이지인지 확인
    isMainPage: false,
    isCategoryPage: false,
    isViewPage: false,

    coverHeaderData: '',
    // 메인 페이지에 뜨는 커버 게시글
    coverArticle: '',
    // 메인 페이지에 뜨는 카테고리 게시글 목록
    coverCategoryData: '',
    // 위에랑 통합할 수 있을듯
    categoryData: '',
    articleData: {
      cover_item_thumbnail: 'https://img1.daumcdn.net/thumb/R1440x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/lNnI9/btqHJj5an3X/An2u3X2N9fSBRQTak6e450/img.jpg',
      cover_item_category: '고궁의 밤',
      cover_item_title: '고궁의 밤 - 경복궁',
      cover_item_simple_date: '2020.09.01',
      writer: 'Skin Demo 6',
      content: "<p>코로나 19로 조선 4대 고궁의 문은 닫히고, <br>계속되던 야간관람과 야간행사도 <br>잠시 멈춰 있습니다. <br><br>못 보니까 더 아쉬운 고궁의 밤, <br>이제서야 소중함을 느끼게 됩니다. <br>조만간 다시 만날 거예요. <br><br>그때까지 잊지 마시라고 <br>경복궁, 창덕궁, 덕수궁, 창경궁의 <br>아름다운 밤을 공개합니다. <br>다시 만나면 더 아껴주세요. <br>우리 고궁의 밤을. </p>",
      like: 0,
      tags: ['경복궁', '고궁', '고궁의 밤', '다음 갤러리'],
      before: {
        link: '64',
        title: '고궁의 밤 - 창덕궁',
      },
      after: {
        link: '66',
        title: '고궁의 밤 - 덕수궁'
      },
    },
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
    }
  }
});