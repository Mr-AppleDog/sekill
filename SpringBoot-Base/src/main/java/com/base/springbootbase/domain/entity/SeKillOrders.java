package com.base.springbootbase.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/31 0:16
 */
@TableName("t_sekill_order")
public class SeKillOrders {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
