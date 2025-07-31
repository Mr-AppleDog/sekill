package com.base.springbootbase.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.springbootbase.common.core.controller.BaseController;
import com.base.springbootbase.common.result.Result;
import com.base.springbootbase.domain.entity.Goods;
import com.base.springbootbase.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description: 商品管理
 * @date 2025/7/30 17:39
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/list")
    public Result list(Goods  goods) {
        List<Goods> list = goodsService.recommendList();
        return Result.success(list);
    }
    @GetMapping("/{goodsId}")
    public Result getInfo(@PathVariable Long goodsId) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", goodsId)
                .select("id", "goods_name", "goods_title", "goods_img", "goods_detail", "goods_price", "goods_stock");
        Goods goods = this.goodsService.getOne(queryWrapper);
        return Result.success(goods);
    }

}
