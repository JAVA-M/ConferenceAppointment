<template>
  <router-view
    :key="key()"
  />
</template>

<script>
export default {
  name: 'MainRouter',
  methods: {
    key() {
      return this.$route.path
    }
  }
}
</script>

<style scoped>

</style>
