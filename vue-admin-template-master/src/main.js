import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/assets/icons' // icon
import '@/permission' // permission control
import directive from './directive' // directive
import plugins from './plugins' // plugins
import { handleTree } from '@/utils/ruoyi'
// 字典标签组件
import DictTag from '@/components/DictTag/index.vue'
// 字典数据组件
import DictData from '@/components/DictData'
// 自定义表格工具组件
import RightToolbar from '@/components/RightToolbar'
// 分页组件
import Pagination from "@/components/Pagination"

import { parseTime, resetForm, addDateRange } from '@/utils/ruoyi'

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */

Vue.prototype.handleTree = handleTree
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}
// 全局方法挂载
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange

// 全局组件挂载
Vue.component('DictTag', DictTag)
Vue.component('RightToolbar', RightToolbar)
Vue.component('Pagination', Pagination)
// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
Vue.use(directive)
Vue.use(plugins)
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)
DictData.install()

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
