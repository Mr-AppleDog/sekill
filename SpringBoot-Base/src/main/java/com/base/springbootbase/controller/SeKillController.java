package com.base.springbootbase.controller;

import com.base.springbootbase.common.constant.UserConstants;
import com.base.springbootbase.common.core.controller.BaseController;
import com.base.springbootbase.common.result.Result;
import com.base.springbootbase.domain.entity.SeKillGoods;
import com.base.springbootbase.service.SeKillGoodsService;
import com.base.springbootbase.service.SeKillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MrLu
 * @version 1.0
 * @description: 商品秒杀
 * @date 2025/7/30 17:38
 */
@RestController
@RequestMapping("/seKill")
public class SeKillController extends BaseController {
    @Autowired
    private SeKillGoodsService seKillService;
    @Autowired
    private SeKillOrderService seKillOrderService;
    @GetMapping("/{goodsId}")
    public Result seKill(@PathVariable Long goodsId) {
        SeKillGoods seKillGoods = seKillService.getOneById(goodsId);
        return Result.success(seKillGoods);
    }
    @GetMapping("/doKill/{goodsId}")
    public Result doKill(@PathVariable Long goodsId) {
        //检测库存
        if(seKillService.checkKillStock(goodsId)<1){
            return Result.error("库存不足");
        }
//        判断订单
        if(UserConstants.NOT_UNIQUE.equals(seKillOrderService.checkOrder(getUserId(),goodsId))){
            return Result.error("您已抢购过");
        }
        // 开始秒杀
        seKillOrderService.seKill(getUserId(),goodsId);

        return Result.success();
    }

}
