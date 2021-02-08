import Vue from 'vue';
import VueRouter from 'vue-router';
import MainView from '@/view/MainView';

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: MainView,
    },
  ]
});