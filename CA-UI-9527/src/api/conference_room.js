import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/data/userinfo/admin/vo/all',
    method: 'get',
    params: query
  })
}

export function fetchRoom(listQuery) {
  return request({
    url: '/data/room/all',
    method: 'post',
    data: listQuery
  })
}

export function allRoom() {
  return request({
    url: '/data/api/room/all',
    method: 'get'
  })
}

export function deleteRoomById(id) {
  return request({
    url: '/data/room/del',
    method: 'post',
    data: { 'id': id }
  })
}

export function queryRoomById(id) {
  return request({
    url: '/data/api/room/queryById',
    method: 'get',
    params: { 'id': id }
  })
}

export function fetchRoomType() {
  return request({
    url: '/data/room/allTypes',
    method: 'get'
  })
}

export function createAdminUser(data) {
  return request({
    url: '/data/userinfo/admin/insert',
    method: 'post',
    data: data
  })
}

export function queryAllRoles() {
  return request({
    url: '/data/api/role/all',
    method: 'get'
  })
}

export function updateRoom(data) {
  return request({
    url: '/data/room/update',
    method: 'post',
    data: data
  })
}

export function createRoom(data) {
  return request({
    url: '/data/room/insert',
    method: 'post',
    data: data
  })
}

export function querySuitableRoom(data) {
  return request({
    url: '/data/room/queryByCounts',
    method: 'get',
    params: { 'count': data }
  })
}

export function queryConferenceRoomAppointStatus(roomId, timeStamp) {
  return request({
    url: '/data/api/room/appointStatus',
    method: 'get',
    params: { 'roomId': roomId, 'timeStamp': timeStamp }
  })
}

export function queryConferenceRoomByCondition(data) {
  return request({
    url: '/data/api/room/condition',
    method: 'post',
    data: data
  })
}

export function queryConferenceRoomType() {
  return request({
    url: '/data/api/type/allTypes',
    method: 'get'
  })
}

export function queryCaCount() {
  return request(({
    url: '/data/api/room/size',
    method: 'get'
  }))
}
