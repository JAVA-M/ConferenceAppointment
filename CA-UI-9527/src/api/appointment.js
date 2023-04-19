import request from '@/utils/request'

export function createAppointment(data) {
  return request({
    url: '/data/appointment/insert',
    method: 'post',
    data: data
  })
}

export function queryAllAppointments() {
  return request({
    url: '/data/appointment/all',
    method: 'get'
  })
}

export function queryAllAppointInfo() {
  return request({
    url: '/data/api/appoint/statistic/all',
    method: 'get'
  })
}

export function queryAppointInfoById(id) {
  return request({
    url: '/data/api/appoint/statistic/queryById',
    method: 'get',
    params: { 'id': id }
  })
}
// 根据预约ID(单号)查询出对应的对象
export function queryAppointDataById(id) {
  return request({
    url: '/data/appointment/queryById',
    method: 'get',
    params: { 'id': id }
  })
}

export function queryAppointMax() {
  return request({
    url: '/data/api/appoint/statistic/total',
    method: 'get'
  })
}

export function queryAppointmentsByCondition(queryCondition) {
  return request({
    url: '/data/appointment/queryByCondition',
    method: 'post',
    data: queryCondition
  })
}

export function queryAllAppointNums() {
  return request({
    url: '/data/appointment/queryAllNums',
    method: 'get'
  })
}

export function queryAppointmentsByUser() {
  return request({
    url: '/data/appointment/queryByUserId',
    method: 'get'
  })
}

export function deleteAppointmentsByID(id) {
  return request({
    url: '/data/appointment/del',
    method: 'post',
    data: { 'id': id }
  })
}

export function updateAppointment(appointment) {
  return request(({
    url: '/data/appointment/update',
    method: 'post',
    data: appointment
  }))
}

export function queryAllWaitCheckInfo() {
  return request(({
    url: '/data/appointment/allUncheck',
    method: 'get'
  }))
}

export function insertCheckInfo(checkInfo) {
  return request(({
    url: '/data/appointment/insert/checkInfo',
    method: 'post',
    data: checkInfo
  }))
}

export function queryAllWaitEvaluate() {
  return request(({
    url: '/data/appointment/waitEvaluate',
    method: 'get'
  }))
}

export function updateAppointStatus(appointId, roomId, score) {
  return request(({
    url: '/data/appointment/updateAppointStatus',
    method: 'post',
    data: { 'appointId': appointId, 'roomId': roomId, 'score': score }
  }))
}

export function exportInfoExcel(userId) {
  return request(({
    url: '/data/appointment/export2',
    method: 'post',
    responseType: 'blob',
    data: { 'appointId': userId }
  }))
}
