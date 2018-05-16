import 'normalize.css';
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui';
import Vue from 'vue';
import App from './App';
import router from './router';

Vue.use(ElementUI);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App),
});
