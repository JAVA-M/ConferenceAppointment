<template>
  <i-frame :src="url" />
</template>

<script>
import IFrame from '@/components/iFrame/index'
export default {
  name: 'Nacos',
  components: { IFrame },
  data() {
    return {
      // url: process.env.VUE_APP_BASE_API + '/druid/login.html'
      // url: 'http://192.168.11.243:18848/nacos/index.html'
      url: 'http://localhost:10004/'
    }
  }
}
</script>

<style scoped>

</style>
