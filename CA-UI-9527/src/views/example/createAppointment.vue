<template>
  <appointment-detail :is-edit="false" />
</template>

<script>
import AppointmentDetail from './components/AppointmentDetail'

export default {
  name: 'CreateAppointment',
  components: { AppointmentDetail }
}
</script>

