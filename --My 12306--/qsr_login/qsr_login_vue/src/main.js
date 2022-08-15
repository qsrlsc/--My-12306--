import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import VueAxios from "vue-axios";
//axios VueAxios 位置不能交换 否则会报错

Vue.config.productionTip = false
Vue.use(ElementUI,{size:"small"});
Vue.use(VueAxios,axios)


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
