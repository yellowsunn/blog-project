import Vue from 'vue'
import App from './App.vue'
import { router } from '@/router';
import { store } from '@/store';
import vOutsideEvents from 'vue-outside-events';
import VModal from 'vue-js-modal';

Vue.config.productionTip = false

Vue.use(vOutsideEvents);
Vue.use(VModal);

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
