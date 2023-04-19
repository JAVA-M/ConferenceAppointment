import { Message } from 'element-ui'

export function success(msg) {
  Message({
    showClose: true,
    message: msg,
    type: 'success',
    duration: 3500
  })
}

export function warning(msg) {
  Message({
    showClose: true,
    message: msg,
    type: 'warning',
    duration: 3500
  })
}

export function error(msg) {
  Message({
    showClose: true,
    message: msg,
    type: 'error',
    duration: 3500
  })
}
