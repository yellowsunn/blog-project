import Vue from 'vue';
import VueRouter from 'vue-router';
import MainView from '@/view/MainView';
import CategoryView from '@/view/CategoryView';
import ArticleView from '@/view/ArticleView';

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: MainView,
    },
    {
      path: '/category/',
      component: CategoryView
    },
    {
      path: '/:articleId',
      component: ArticleView
    },
    {
      path: '/category/:categoryId',
      component: CategoryView
    }
  ]
});