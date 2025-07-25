import request from '@/utils/request'

/**
 * 登录方法
 * @param data
 * @returns {*}
 */
export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

/**
 * 获取用户信息
 * @param token
 * @returns {*}
 */
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}
