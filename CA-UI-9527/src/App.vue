<template>
  <div id="app">
    <router-view v-if="isRouterAlive" />
  </div>
</template>

<script>
import { v4 } from 'uuid' // npm install -S uuid

export default {
  name: 'App',
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reload() { // vue 刷新方法，不会出现空白页面
      this.isRouterAlive = false
      this.$nextTick(function() {
        this.isRouterAlive = true
      })
    }
  },
  created() {
    /* 生成前端设备号 */
    /* if (window.localStorage.getItem('webId') === null || window.localStorage.getItem('webId') === '') {
      window.localStorage.setItem('webId', v4())
    } */
    // console.log('UUID', v4())
  }
}
</script>
