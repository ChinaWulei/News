import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "../views/Front/Login.vue";
import Register from "../views/Front/Register.vue";
import Main from "../views/Front/Main.vue";
import Detail from "../views/Front/Detail.vue";

import AdminLogin from "../views/Back/AdminLogin.vue";
import AdminMain from "../views/Back/AdminMain.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/main/:pid',
      name: 'main',
      component: Main
    },
    {
      path: '/detail',
      name: 'detail',
      component: Detail
    },

    {
      path: '/AdminLogin',
      name: 'AdminLogin',
      component: AdminLogin
    },
    {
      path: '/AdminMain',
      name: 'AdminMain',
      component: AdminMain
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },

  ]
})

export default router
