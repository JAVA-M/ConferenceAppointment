import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/auth-redirect', '/', '/page', '/index', '/webHome'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      // console.log('========> login')
      // next({ path: '/' })
      NProgress.done() // hack: https://github.com/PanJiaChen/vue-element-admin/pull/2939
    } else {
      // determine whether the user has obtained his permission roles through getInfo
      const hasRoles = store.getters.roles && store.getters.roles.length > 0
      if (hasRoles) {
        next()
      } else {
        try {
          // get user info
          // note: roles must be a object array! such as: ['admin'] or ,['developer','editor']
          const { roles } = await store.dispatch('user/getInfo')
          console.log('roles:', roles)
          // generate accessible routes map based on roles
          const accessRoutes = await store.dispatch('permission/generateRoutes', roles)
          console.log('routes:', accessRoutes)
          // dynamically add accessible routes
          router.addRoutes(accessRoutes)

          // hack method to ensure that addRoutes is complete
          // set the replace: true, so the navigation will not leave a history record
          next({ ...to, replace: true })
        } catch (error) {
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error.message || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      console.log('to:', to.path)
      if (to.path === '/login') {
        // 只有访问/login的时候才会跳转到登录页面
        // other pages that do not have permission to access are redirected to the login page.
        next(`/login?redirect=${to.path}`)
      } else { // 如果是非公共路由，不能访问，则自动跳转至首页
        next(`/webHome?redirect=${to.path}`)
        // 特殊处理/articleDetails/:id，携带id路由不匹配防止重定向首页问题，没有找到好的方法解决
        /* console.log('to.path.indexOf(\'/index/\')', to.path.indexOf('/index/'))
        if (to.path.indexOf('/index/') !== -1) {
          next()
        } else {
          console.log('==============>Index2')
          // 否则全部跳转到index路由。博客页面
          next(`/index?redirect=${to.path}`)
          NProgress.done()
        } */
      }
      NProgress.done()
    }
    /* has no token*/
    /* if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    } */
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
