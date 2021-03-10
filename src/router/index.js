import Vue from 'vue';
import VueRouter from 'vue-router';
import MainView from '@/view/MainView';
import CategoryView from '@/view/CategoryView';
import ArticleView from '@/view/ArticleView';
import SearchView from '@/view/SearchView';
import LoginView from '@/view/LoginView';
import CreateArticleView from '@/view/CreateArticleView';

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
    }
  ]
});