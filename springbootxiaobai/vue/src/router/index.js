import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/Manage.vue'),
    redirect: "/home",
    children: [
      { path: 'home', name: 'Home', component: () => import('../views/Home.vue') },
      { path: 'user', name: 'User', component: () => import('../views/User.vue') }
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName" , to.name);  //设置的当前路由名称 ，为了在 Header 中使用
  store.commit("setPath");  // 触发 store 的数据更新
  next(); //放行路由
})

export default router
