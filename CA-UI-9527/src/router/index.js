import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import AppointmentIndex from '@/views/appointmentViews/Index'
/* Router Modules */

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/',
    redirect: '/webHome',
    hidden: true
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/index',
    component: () => import('@/views/appointmentViews/Index'),
    name: 'index',
    hidden: true, /* hidden 代表是否在侧边栏显示该条路由 */
    redirect: '/webHome',
    meta: { title: 'index', roles: ['xxx'] },
    children: [
      {
        path: '/webHome',
        component: () => import('@/views/appointmentViews/container/WebContainerMain2'),
        name: 'home',
        meta: { title: 'index', roles: ['xxx'] }
      },
      /* 展示会议室详情路由 */
      {
        path: '/roomDetails/:id',
        name: 'roomDetails',
        component: () => import('@/views/appointmentViews/details/RoomDetailsBody'),
        hidden: true,
        meta: { title: 'roomDetails' }
      },
      /* 用户主页 */
      {
        path: '/userHome',
        name: 'userHome',
        component: () => import('@/views/appointmentViews/details/PersonalHome'),
        hidden: true,
        meta: { title: 'userHome' }
      },
      /* 留言 */
      {
        path: '/leavingMessage',
        name: 'leavingMessage',
        component: () => import('@/views/appointmentViews/message/leavingMessage/LeavingMessage'),
        hidden: true,
        meta: { title: 'leavingMessage' }
      },
      {
        path: '/evaluate',
        name: 'evaluate',
        component: () => import('@/views/appointmentViews/details/MarkScore'),
        hidden: true,
        meta: { title: 'evaluate' }
      }
    ]
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  /* {
    path: '/guide',
    component: Layout,
    redirect: '/guide/index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/guide/index'),
        name: 'Guide',
        meta: { title: 'Guide', icon: 'guide', noCache: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: { title: 'Profile', icon: 'user', noCache: true }
      }
    ]
  }, */
  {
    path: '/index',
    component: AppointmentIndex,
    redirect: '/index/home',
    hidden: true,
    children: [
      /* 首页路由 */
      {
        path: 'home',
        component: () => import('@/views/appointmentViews/page'),
        // hidden: true,
        meta: { title: 'index' }
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: '/webHome',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: '控制台面板',
        meta: { title: '控制台面板', icon: 'dashboard', affix: true }
      }, {
        path: 'webHome',
        component: () => import('@/views/appointmentViews/container/WebContainerMain2'),
        name: '首页',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }/* ,
      {
        path: 'page',
        component: () => import('@/views/appointmentViews/page'),
        name: 'page',
        meta: { title: 'index' }
      } */
    ]
  },
  {
    path: '/documentation',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/appointmentViews/api/swagger/index'),
        name: 'API文档',
        meta: { title: 'API文档', icon: 'documentation', affix: true }
      }
    ]
  },
  {
    path: '/example',
    component: Layout,
    redirect: '/example/list',
    name: 'Example',
    meta: {
      title: '用户数据',
      icon: 'user'
    },
    children: [
      {
        path: 'create',
        component: () => import('@/views/example/create'),
        name: 'CreateArticle',
        meta: { title: '创建用户', icon: 'edit' }
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/example/edit'),
        name: 'EditArticle',
        meta: { title: 'Edit Article', noCache: true, activeMenu: '/example/list' },
        hidden: true
      },
      {
        path: 'list',
        component: () => import('@/views/example/list'),
        name: 'ArticleList',
        meta: { title: '系统用户', icon: 'el-icon-s-custom' }
      }
    ]
  },
  {
    path: '/conferenceRoom',
    component: Layout,
    redirect: '/conferenceRoom/list',
    name: 'conferenceRoom',
    meta: {
      title: '会议室数据',
      icon: 'el-icon-s-help'
    },
    children: [
      {
        path: 'create',
        component: () => import('@/views/example/create'),
        name: 'CreateArticle',
        meta: { title: '创建会议室', icon: 'edit' }
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/example/edit'),
        name: 'EditArticle',
        meta: { title: 'Edit Article', noCache: true, activeMenu: '/example/list' },
        hidden: true
      },
      {
        path: 'list',
        component: () => import('@/views/example/room'),
        name: 'ConferenceRoom',
        meta: { title: '会议室信息', icon: 'el-icon-s-home' }
      }
    ]
  },
  {
    path: '/appointment',
    component: Layout,
    redirect: '/appointment/list',
    name: 'appointment',
    meta: {
      title: '会议预约',
      icon: 'table'
    },
    children: [
      {
        path: 'createAppointment',
        component: () => import('@/views/example/createAppointment'),
        name: 'CreateAppointment',
        meta: { title: '创建预约', icon: 'edit' }
      },
      {
        path: 'list',
        component: () => import('@/views/example/appointments'),
        name: 'ConferenceAppointment',
        meta: { title: '全部信息', icon: 'el-icon-s-claim' }
      },
      {
        path: 'check',
        component: () => import('@/views/example/appointCheck'),
        name: 'AppointCheck',
        meta: { title: '待审核', icon: 'el-icon-s-claim' }
      }
    ]
  },
  {
    path: '/monitor',
    component: Layout,
    redirect: '/monitor/serverInfo',
    name: 'monitor',
    meta: {
      title: '系统监控',
      icon: 'el-icon-monitor'
    },
    children: [
      {
        path: 'serverInfo',
        component: () => import('@/views/monitor/server/Server'),
        name: 'Server',
        meta: { title: '系统监控', icon: 'el-icon-s-platform' }
      },
      {
        path: 'druidInfo',
        component: () => import('@/views/monitor/druid/Druid'),
        name: 'druidInfo',
        meta: { title: '数据监控', icon: 'data' }
      },
      {
        path: 'cacheInfo',
        component: () => import('@/views/monitor/cache/index'),
        name: 'cacheInfo',
        meta: { title: '缓存监控', icon: 'redis_big' }
      },
      {
        path: 'nacosInfo',
        component: () => import('@/views/monitor/nacos/index'),
        name: 'nacosInfo',
        meta: { title: '服务监控', icon: 'redis_big' }
      }
    ]
  }
]
/* export const asyncRoutes = [
  {
    path: '/icon',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/icons/index'),
        name: 'Icons',
        meta: { title: 'Icons', icon: 'icon', noCache: true }
      }
    ]
  },
  {
    path: '/error',
    component: Layout,
    redirect: 'noRedirect',
    name: 'ErrorPages',
    meta: {
      title: 'Error Pages',
      icon: '404'
    },
    children: [
      {
        path: '401',
        component: () => import('@/views/error-page/401'),
        name: 'Page401',
        meta: { title: '401', noCache: true }
      },
      {
        path: '404',
        component: () => import('@/views/error-page/404'),
        name: 'Page404',
        meta: { title: '404', noCache: true }
      }
    ]
  },

  {
    path: '/error-log',
    component: Layout,
    children: [
      {
        path: 'log',
        component: () => import('@/views/error-log/index'),
        name: 'ErrorLog',
        meta: { title: 'Error Log', icon: 'bug' }
      }
    ]
  },

  {
    path: '/theme',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/theme/index'),
        name: 'Theme',
        meta: { title: 'Theme', icon: 'theme' }
      }
    ]
  },

  {
    path: '/clipboard',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/clipboard/index'),
        name: 'ClipboardDemo',
        meta: { title: 'Clipboard', icon: 'clipboard' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://github.com/PanJiaChen/vue-element-admin',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]*/

export const personalRouter = [
  /* 博客展示页面相关路由 start */
  {
    path: '/index',
    component: AppointmentIndex,
    redirect: '/index/home',
    hidden: true,
    children: [
      /* 首页路由 */
      {
        path: 'home',
        component: () => import('@/views/appointmentViews/page'),
        // hidden: true,
        meta: { title: 'index' }
      }
    ]
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  // routes: [constantRoutes, personalRouter]
  // routes: personalRouter
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
