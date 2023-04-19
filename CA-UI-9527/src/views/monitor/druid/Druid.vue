<template>
  <i-frame :src="url" />
</template>

<script>
import IFrame from '@/components/iFrame/index'
export default {
  name: 'Druid',
  components: { IFrame },
  data() {
    return {
      // url: process.env.VUE_APP_BASE_API + '/druid/login.html'
      url: 'http://localhost:10001/druid/login.html'
    }
  }
}
</script>

<style scoped>

</style>
