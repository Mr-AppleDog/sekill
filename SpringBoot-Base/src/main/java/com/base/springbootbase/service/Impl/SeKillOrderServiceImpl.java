package com.base.springbootbase.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.base.springbootbase.common.constant.UserConstants;
import com.base.springbootbase.domain.entity.Goods;
import com.base.springbootbase.domain.entity.Order;
import com.base.springbootbase.domain.entity.SeKillGoods;
import com.base.springbootbase.domain.entity.SeKillOrders;
import com.base.springbootbase.mapper.OrderMapper;
import com.base.springbootbase.mapper.SeKillGoodsMapper;
import com.base.springbootbase.mapper.SeKillOrderMapper;
import com.base.springbootbase.service.SeKillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/30 23:59
 */
@Service
public class SeKillOrderServiceImpl implements SeKillOrderService {
    @Autowired
    private SeKillOrderMapper seKillOrderMapper;
    @Autowired
    private SeKillGoodsMapper seKillGoodsMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 检查秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public String checkOrder(Long userId, Long goodsId) {
        QueryWrapper<SeKillOrders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("goods_id", goodsId);
        Long l = seKillOrderMapper.selectCount(queryWrapper);
        if (l > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 秒杀服务
     * @param userId
     * @param goodsId
     */
    @Override
    @Transactional
    public void seKill(Long userId, Long goodsId) {
        //获取秒杀商品
        SeKillGoods seKillGoods = seKillGoodsMapper.getById(goodsId);
        //检查库存
        LambdaUpdateWrapper<SeKillGoods> lambdaUpdate = new LambdaUpdateWrapper<>();
        lambdaUpdate.eq(SeKillGoods::getId, goodsId)
                        .set(SeKillGoods::getKillStock, seKillGoods.getKillStock()-1);
//        seKillGoods.setKillStock(seKillGoods.getKillStock()-1);
        seKillGoodsMapper.update(null,lambdaUpdate);

        //生成订单
        Order order = new Order();
        order.setUserId(userId);
        order.setGoodsId(goodsId);
        order.setAddressId(1L);
        order.setGoodsName(seKillGoods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seKillGoods.getKillPrice());
        order.setOrderChannel(1);
        order.setOrderStatus(0);
        order.setCreateTime(LocalDateTime.now());
        orderMapper.insert(order);

        // 生成秒杀订单
        SeKillOrders seKillOrders = new SeKillOrders();
        seKillOrders.setUserId(userId);
        seKillOrders.setGoodsId(goodsId);
        seKillOrders.setOrderId(order.getId());
        seKillOrderMapper.insert(seKillOrders);

    }
}
