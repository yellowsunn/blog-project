import Vue from 'vue';
import { store } from '@/store';
import VueRouter from 'vue-router';
import MainView from '@/view/MainView';
import CategoryView from '@/view/CategoryView';
import ArticleView from '@/view/ArticleView';
import SearchView from '@/view/SearchView';
import LoginView from '@/view/LoginView';
import CreateArticleView from '@/view/CreateArticleView';
import ManageView from '@/view/ManageView';
import ManageHeader from '@/components/manage/ManageHeader';
import ManageProfile from '@/components/manage/ManageProfile';
import ManageCover from '@/components/manage/ManageCover';
import ManageCategory from '@/components/manage/ManageCategory';
import EditCategory from '@/components/manage/EditCategory';
import CommentHistory from '@/components/manage/CommentHistory';

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: MainView,
      beforeEnter: async (to, from, next) => {
        await beforeMainView();
        next();
      }
    },
    {
      path: '/login',
      component: LoginView,
    },
    {
      path: '/newpost/',
      component: CreateArticleView,
    },
    {
      path: '/category/',
      component: CategoryView,
      beforeEnter: async (to, from, next) => {
        await beforeCategoryView(to);
        next();
      }
    },
    {
      path: '/search/',
      component: SearchView
    },
    {
      path: '/manage',
      component: ManageView,
      beforeEnter: async (to, from, next) => {
          await store.dispatch('GET_AUTHORITY');
          if (store.state.isAuthorized) {
            next();
          } else {
            next("/login");
          }
      }
    },
    {
      path: '/:articleId',
      component: ArticleView,
      beforeEnter: async (to, from, next) => {
        await beforeArticleView(to);
        next();
      }
    },
    {
      path: '/newpost/:articleId',
      component: CreateArticleView,
    },
    {
      path: '/category/:categoryId',
      component: CategoryView,
      beforeEnter: async (to, from, next) => {
        await beforeCategoryView(to);
        next();
      }
    },
    {
      path: '/search/:search',
      component: SearchView,
      beforeEnter: async (to, from, next) => {
        await beforeSearchView(to);
        next();
      }
    },
    {
      path: '/manage/header',
      component: ManageHeader
    },
    {
      path: '/manage/profile',
      component: ManageProfile
    },
    {
      path: '/manage/cover',
      component: ManageCover
    },
    {
      path: '/manage/category',
      component: ManageCategory
    },
    {
      path: '/manage/category/edit',
      component: EditCategory
    },
    {
      path: '/manage/category/edit/:categoryId',
      component: EditCategory
    },
    {
      path: '/manage/comment_history',
      component: CommentHistory
    }
  ]
});

const beforeMainView = async () => {
  store.state.isMainPage = true;
  store.state.isCategoryPage = false;
  store.state.isViewPage = false;

  await store.dispatch('GET_ALL_MAIN_VIEW_DATA');
};

const beforeArticleView = async (to) => {
  store.state.isViewPage = true;
  store.state.isMainPage = false;
  store.state.isCategoryPage = false;
  document.body.id = "tt-body-page";

  await store.dispatch('GET_ALL_ARTICLE_VIEW_DATA', to.params.articleId);
};

const beforeCategoryView = async (to) => {
  store.state.isCategoryPage = true;
  store.state.isMainPage = false;
  store.state.isViewPage = false;
  document.body.id = "tt-body-category"
  document.body.classList.add('headerbannerdisplayon'); // 메인페이지 배너 이미지 사라짐

  await store.dispatch('GET_ALL_CATEGORY_VIEW_DATA', {
    categoryId: to.params.categoryId,
    page: to.query.page,
  });
}

const beforeSearchView = async (to) => {
  store.state.isCategoryPage = true;

  document.body.id = "tt-body-search";
  document.body.classList.add('headerbannerdisplayon'); // 메인페이지 배너 이미지 사라짐

  await store.dispatch('GET_ALL_SEARCH_VIEW_DATA', {
    search: to.params.search,
    page: to.query.page,
  });
}