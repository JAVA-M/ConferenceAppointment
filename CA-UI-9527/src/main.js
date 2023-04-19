import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets
import './assets/font/custom_font.css' // 自定义字体
import Element from 'element-ui'
import './styles/element-variables.scss'
// import enLang from 'element-ui/lib/locale/lang/en'// 如果使用中文语言包请默认支持，无需额外引入，请删除该依赖
import '@/assets/icon_font/iconfont.css'
import '@/assets/icon_font/iconfont.js'
import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import './icons' // icon
import './permission' // permission control
import './utils/error-log' // error log

import * as filters from './filters' // global filters
import { Message } from 'element-ui'

Vue.prototype.$message = Message
import Antd from 'ant-design-vue'
// import 'ant-design-vue/dist/antd.css'
import 'ant-design-vue/dist/antd.less'
// 图片大图预览
// import Viewer from 'v-viewer' // 引入js
// import 'viewerjs/dist/viewer.css' // 引入css
// Vue.use(Viewer) // 默认配置写法


import VueViewer, { directive as viewerDirective } from 'v-viewer';
import 'viewerjs/dist/viewer.css'; // 图片预览器的样式
Vue.use(VueViewer, {
  defaultOptions: {
    /**
     *  数字含义:
     *  0 or false: hide the navbar
     1 or true: show the navbar
     2: show the navbar only when the screen width is greater than 768 pixels
     3: show the navbar only when the screen width is greater than 992 pixels
     4: show the navbar only when the screen width is greater than 1200 pixels
     */
    toolbar: {
      zoomIn: 0,
      zoomOut: 0,
      oneToOne: 0,
      reset: 0,
      prev: {
        show: 4,
        size: 'large'
      },
      play: 0,
      next: {
        show: 4,
        size: 'large'
      },
      rotateLeft: 0,
      rotateRight: 0,
      flipHorizontal: 0,
      flipVertical: 0
    },
    zoomRatio: 0.3 // 缩放比率 默认是0.1
  }
}) // 图片预览器
// 用于图片预览的指令方式调用 在元素上加上会处理元素下所有的图片,为图片添加点击事件,点击即可预览
Vue.directive('viewer', viewerDirective({
  debug: true
}))

Vue.use(Antd)
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

Vue.use(Element, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
  /* locale: enLang // 如果使用中文，无需设置，请删除 */
})

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
