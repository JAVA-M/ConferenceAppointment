import request from '@/utils/request'

export function getAction(url, params) {
  return request({
    url: url,
    method: 'get',
    params: params,
    headers: {
      'token': localStorage.getItem('token'),
      'name': 'mxf'
    }
  })
}

/**
 * 获取文件服务访问路径
 * @param avatar
 * @param subStr
 * @returns {*}
 */
export function getFileAccessHttpUrl(avatar, subStr) {
  if (!subStr) subStr = 'http'
  if (avatar && avatar.startsWith(subStr)) {
    return avatar
  } else {
    if (avatar && avatar.length > 0 && avatar.indexOf('[') === -1) {
      // return process.env.VUE_APP_BASE_API + 'https://wxmin.cn/ants/sys/common/static/' + avatar
      return 'https://wxmin.cn/ants/sys/common/static/' + avatar
    }
  }
}

