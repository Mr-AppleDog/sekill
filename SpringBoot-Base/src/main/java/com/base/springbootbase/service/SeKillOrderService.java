package com.base.springbootbase.service;

import com.base.springbootbase.domain.entity.SeKillGoods;

/**
 * @author MrLu
 * @version 1.0
 * @description:  秒杀
 * @date 2025/7/30 17:50
 */
public interface SeKillOrderService {

    /**
     * 检查秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    String checkOrder(Long userId, Long goodsId);

    /**
     * 秒杀
     * @param userId
     * @param goodsId
     */
    void seKill(Long userId, Long goodsId);
}
