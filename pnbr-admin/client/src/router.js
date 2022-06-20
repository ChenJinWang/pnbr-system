import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: 'login'
    },
    {
      path: '/index',
      name: 'Index',
      component: () => import('./views/Index.vue'),
      meta: { title: '首页' },
      redirect: '/home',
      children: [
        {
          path: '/home',
          name: 'home',
          component: () => import('./views/Home/Home'),
          meta: { title: '首页' }
        },
        {
          path: '/staff',
          name: 'staff',
          component: () => import('./views/Staff'),
          meta: { title: '管理员列表' }
        },
        {
          path: '/userList',
          name: 'userList',
          component: () => import('./views/userList'),
          meta: { title: '用户列表' }
        },
        {
          path: '/Infoshow',
          name: 'Infoshow',
          component: () => import('./views/information/InfoShow'),
          meta: { title: '个人信息' }
        },
        {
          path: '/categoryList',
          name: 'categoryList',
          component: () => import('./views/pet/categoryList'),
          meta: { title: '分类列表' }
        },
        {
          path: '/petList',
          name: 'petList',
          component: () => import('./views/pet/petList'),
          meta: { title: '商品审核列表' }
        },
        {
          path: '/community',
          name: 'community',
          component: () => import('./views/pet/community'),
          meta: { title: '帖子审核列表' }
        },
        {
          path: '/theAgency',
          name: 'theAgency',
          component: () => import('./views/pet/theAgency'),
          meta: { title: '反馈列表' }
        },
        {
          path: '/orderList',
          name: 'orderList',
          component: () => import('./views/fundmanagement/orderList'),
          meta: { title: '订单列表' }
        },
      ]
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('./views/register/Register')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/logo/Login')
    },
    {
      path: '/lock',
      name: 'lock',
      component: () => import('./views/Lock.vue')
    },
    {
      path: '*',
      name: 'Nofind',
      component: () => import('./views/404')
    }
  ]
  // mode: "history"
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const isLogin = !!localStorage.eleToken

  if (to.path === '/login' || to.path === '/register') {
    next()
  } else {
    isLogin ? next() : next('/login')
  }
})

export default router
