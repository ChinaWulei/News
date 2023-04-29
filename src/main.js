import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'



import './assets/main.css'
import './assets/global.css'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import axios from "./network/axios.js";
import Vuex from "vuex";

import * as ElementPlusIconsVue from '@element-plus/icons-vue'




const app = createApp(App)

app.use(Vuex)
app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.config.globalProperties.$axios=axios;  //配置axios的全局引用
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.mount('#app')
