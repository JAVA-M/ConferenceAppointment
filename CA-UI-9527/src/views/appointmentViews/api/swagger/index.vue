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
      url: 'http://localhost:10010/swagger-ui/index.html'
    }
  }
}
</script>

<style scoped>

</style>
