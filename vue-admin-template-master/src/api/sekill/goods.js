import request from '@/utils/request'

/**
 * 商品列表
 * @param data
 * @returns {*}
 */
export function goodsList() {
  return request({
    url: '/goods/list',
    method: 'get'
  })
}
/**
 * 商品详情页
 * @param data
 * @returns {*}
 */
export function goodsById(goodsId) {
  return request({
    url: '/seKill/' + goodsId,
    method: 'get'
  })
}
/**
 * 商品详情页
 * @param data
 * @returns {*}
 */
export function doSeKill(goodsId) {
  return request({
    url: '/seKill/doKill/' + goodsId,
    method: 'get'
  })
}
