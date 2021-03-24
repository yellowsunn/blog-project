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
      component: CategoryView
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
      component: ArticleView
    },
    {
      path: '/newpost/:articleId',
      component: CreateArticleView,
    },
    {
      path: '/category/:categoryId',
      component: CategoryView
    },
    {
      path: '/search/:search',
      component: SearchView
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