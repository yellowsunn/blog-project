import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    // aside toggle
    asideOn: false,
    // 메인 페이지, 카테고리 페이지인지 확인
    isMainPage: false,
    isCategoryPage: false,
    isViewPage: false,

    coverHeaderData: {
      title: '문화재청',
      slogunTitle: '어제를 담아 내일에 전합니다.',
      slogunText: '과거와 현재, 시간과 사람 사이에 다리를 놓아<br>우리 시대의 문화를 만들어갑니다.',
      categories: [
        { name: "홈", link: "/", },
        { name: "고궁의 밤", link: "#" },
        { name: "행사 안내", link: "#" },
      ]
    },
    // 메인 페이지에 뜨는 커버 게시글
    coverData: {
      cover_title: "고궁의 밤",
      cover_item_url: '#',
      cover_item_thumbnail: 'https://i1.daumcdn.net/thumb/S640x460/?fname=https://blog.kakaocdn.net/dn/lNnI9/btqHJj5an3X/An2u3X2N9fSBRQTak6e450/img.jpg',
      cover_item_title: '고궁의 밤 - 경복궁',
      cover_item_summary: '코로나 19로 조선 4대 고궁의 문은 닫히고, 계속되던 야간관람과 야간행사도 잠시 멈춰 있습니다. 못 보니까 더 아쉬운 고궁의 밤, 이제서야 소중함을 느끼게 됩니다. 조만간 다시 만날 거예요. 그때까지 잊지 마시라고 경복궁, 창덕궁, 덕수궁, 창경궁의 아름다운 밤을 공개합니다. 다시 만나면 더 아껴주세요. 우리 고궁의 밤을. 경복궁  경복궁 景福宮 사적 제117호 경복궁은 1395년(태조..',
      cover_item_category: '고궁의 밤',
      cover_item_simple_date: '2020.09.01',
      cover_item_comment_count: '0',
    },
    // 메인 페이지에 뜨는 카테고리 게시글 목록
    coverCategoryData: {
      cover_title: "문화재청 소식",
      cover_url: "#",
      item: [
        {
          cover_item_url: "#",
          cover_item_thumbnail: 'https://i1.daumcdn.net/thumb/S160x108/?fname=https://blog.kakaocdn.net/dn/oraF1/btqHAmWduTn/9oCrT8yOPrsktnRFKqXpKk/img.jpg',
          cover_item_title: '세계에 활짝 열린 실감형 궁궐체험 앱 ‘창덕궁’',
          cover_item_summary: "문화재청 ㆍ SK텔레콤 ㆍ 구글코리아, 5G AR앱 ‘창덕 아리랑(AR-irang)’앱 28일 공개  문화재청 궁능유적본부 창덕궁관리소는 SK텔레콤과 함께 실감형 궁궐체험 프로그램으로 5세대 이동통신(이하 ‘5G’) 증강현실(이하 AR) 애플리케이션인 ‘창덕 아리랑(AR-irang)’을 개발해 27일 기념행사를 개최하고, 28일 일반에 공개합니다. ​  ‘창덕 아리랑(AR-irang)’은 우리나라의 대표적인 문화유산이자 조선 5대 궁..",
          cover_item_category: "정책 소식",
          cover_item_simple_date: "2020.09.01",
          cover_item_comment_count: "0",
        },
        {
          cover_item_url: '#',
          cover_item_thumbnail: 'https://i1.daumcdn.net/thumb/S640x460/?fname=https://blog.kakaocdn.net/dn/lNnI9/btqHJj5an3X/An2u3X2N9fSBRQTak6e450/img.jpg',
          cover_item_title: '고궁의 밤 - 경복궁',
          cover_item_summary: '코로나 19로 조선 4대 고궁의 문은 닫히고, 계속되던 야간관람과 야간행사도 잠시 멈춰 있습니다. 못 보니까 더 아쉬운 고궁의 밤, 이제서야 소중함을 느끼게 됩니다. 조만간 다시 만날 거예요. 그때까지 잊지 마시라고 경복궁, 창덕궁, 덕수궁, 창경궁의 아름다운 밤을 공개합니다. 다시 만나면 더 아껴주세요. 우리 고궁의 밤을. 경복궁  경복궁 景福宮 사적 제117호 경복궁은 1395년(태조..',
          cover_item_category: '고궁의 밤',
          cover_item_simple_date: '2020.09.01',
          cover_item_comment_count: '0',
        }
      ]
    },
    // 위에랑 통합할 수 있을듯
    categoryData: {
      list_conform: "전체 글 보기",
      list_count: "19",
      item: [
        {
          cover_item_url: "#",
          cover_item_thumbnail: 'https://i1.daumcdn.net/thumb/S160x108/?fname=https://blog.kakaocdn.net/dn/oraF1/btqHAmWduTn/9oCrT8yOPrsktnRFKqXpKk/img.jpg',
          cover_item_title: '세계에 활짝 열린 실감형 궁궐체험 앱 ‘창덕궁’',
          cover_item_summary: "문화재청 ㆍ SK텔레콤 ㆍ 구글코리아, 5G AR앱 ‘창덕 아리랑(AR-irang)’앱 28일 공개  문화재청 궁능유적본부 창덕궁관리소는 SK텔레콤과 함께 실감형 궁궐체험 프로그램으로 5세대 이동통신(이하 ‘5G’) 증강현실(이하 AR) 애플리케이션인 ‘창덕 아리랑(AR-irang)’을 개발해 27일 기념행사를 개최하고, 28일 일반에 공개합니다. ​  ‘창덕 아리랑(AR-irang)’은 우리나라의 대표적인 문화유산이자 조선 5대 궁..",
          cover_item_category: "정책 소식",
          cover_item_simple_date: "2020.09.01",
          cover_item_comment_count: "0",
        },
        {
          cover_item_url: '#',
          cover_item_thumbnail: 'https://i1.daumcdn.net/thumb/S640x460/?fname=https://blog.kakaocdn.net/dn/lNnI9/btqHJj5an3X/An2u3X2N9fSBRQTak6e450/img.jpg',
          cover_item_title: '고궁의 밤 - 경복궁',
          cover_item_summary: '코로나 19로 조선 4대 고궁의 문은 닫히고, 계속되던 야간관람과 야간행사도 잠시 멈춰 있습니다. 못 보니까 더 아쉬운 고궁의 밤, 이제서야 소중함을 느끼게 됩니다. 조만간 다시 만날 거예요. 그때까지 잊지 마시라고 경복궁, 창덕궁, 덕수궁, 창경궁의 아름다운 밤을 공개합니다. 다시 만나면 더 아껴주세요. 우리 고궁의 밤을. 경복궁  경복궁 景福宮 사적 제117호 경복궁은 1395년(태조..',
          cover_item_category: '고궁의 밤',
          cover_item_simple_date: '2020.09.01',
          cover_item_comment_count: '0',
        }
      ]
    },
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
  },
  mutations: {
  }
});