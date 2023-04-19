import request from '@/utils/request'

export function queryAllLeaveMessage(pagination) {
  return request({
    url: '/data/api/message/leaveMessage/all',
    method: 'get',
    params: { ...pagination }
  })
}

export function queryLeaveMessageSize() {
  return request({
    url: '/data/api/message/leaveMessage/size',
    method: 'get'
  })
}

export function insertLeaveMessage(message) {
  return request({
    url: '/data/api/message/leaveMessage/insert',
    method: 'post',
    data: message
  })
}
