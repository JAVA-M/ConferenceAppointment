import axios from 'axios'

export function queryWeather() {
  return axios.get('https://api.vvhan.com/api/weather?city=深圳市')
}

export function queryVisitorInfo() {
  return axios.get('https://api.vvhan.com/api/visitor.info')
}

export function queryQQInfo(qq) {
  return axios.get('https://api.vvhan.com/api/qq?qq=' + qq)
}
