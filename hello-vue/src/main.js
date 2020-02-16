// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'

axios.defaults.baseURL = '/api'; // 设置axios的基础请求路径
axios.defaults.timeout = 2000; // 设置axios的请求时间

Vue.use(ElementUI);

Vue.config.productionTip = false

Vue.prototype.$axios = axios;// 将axios赋值给Vue原型的$axios属性，这样所有vue实例都可使用该对象
/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>'
})

/*

index.html -> main.js ->
*/
