import Vue from 'vue'
import App from './App.vue'
import { router } from '@/router';
import { store } from '@/store';
import vOutsideEvents from 'vue-outside-events';

Vue.config.productionTip = false

Vue.use(vOutsideEvents);

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
