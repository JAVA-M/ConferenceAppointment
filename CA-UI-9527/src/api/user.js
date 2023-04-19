import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/data/userinfo/getUserVoInfo',
    method: 'get'
  })
}

export function queryNameIsRepeat(username) {
  return request({
    url: '/data/api/user/queryNameRepeat',
    method: 'get',
    params: { 'username': username }
  })
}

export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}

export function createUser(data) {
  return request({
    url: '/auth/register',
    method: 'post',
    data: data
  })
}

export function refreshVerifyCode(webId) {
  return request({
    url: '/auth/captcha',
    method: 'get',
    params: { 'webId': webId }
  })
}
