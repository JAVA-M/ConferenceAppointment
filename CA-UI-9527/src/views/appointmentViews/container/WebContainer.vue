<template>
  <div class="joe_container">
    <main-router />
    <web-container-aside v-if="showAside()" />
  </div>
</template>

<script>
import WebContainerMain from '@/views/appointmentViews/container/WebContainerMain'
import WebContainerAside from '@/views/appointmentViews/container/WebContainerAside'
import MainRouter from '@/views/appointmentViews/webRouter/MainRouter'

export default {
  name: 'WebContainer',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    WebContainerMain,
    WebContainerAside,
    MainRouter
  },
  data() {
    return {}
  },
  created() {
  },
  methods: {
    key() {
      return this.$route.path
    },
    showAside() {
      const path = this.$route.path
      console.log(path)
      if (path === '/articleArchive' ||
        path === '/userHome' ||
        path === '/leavingMessage' ||
        path === '/chat' ||
        path.indexOf('/userHome/') !== -1 ||
        path.indexOf('/chat/') !== -1 ||
        path.indexOf('/leavingMessage/') !== -1 ||
        path.indexOf('/roomDetails/') !== -1) {
        return false
      }

      return true
    }
  }

}
</script>

