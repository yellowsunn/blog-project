import Vue from 'vue'
import App from './App.vue'
import { router } from '@/router';
import { store } from '@/store';
import vOutsideEvents from 'vue-outside-events';
import VModal from 'vue-js-modal';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

Vue.use(vOutsideEvents);
Vue.use(VModal);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
