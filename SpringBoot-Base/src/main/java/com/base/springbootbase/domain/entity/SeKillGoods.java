package com.base.springbootbase.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author MrLu
 * @version 1.0
 * @description: 秒杀商品表
 * @date 2025/7/30 19:07
 */
@TableName("t_sekill_goods")
public class SeKillGoods {
    /** 秒杀ID */
    @TableId
    private Long id;
    /** 商品ID */
    private Long goodsId;
    /** 商品名称 */
    private String goodsName;
    /** 商品标题 */
    private String goodsTitle;
    /** 商品图片 */
    private String goodsImg;
    /** 商品描述 */
    private String goodsDetail;
    /** 商品价格 */
    private Double goodsPrice;
    /** 商品库存，-1表示没有限制 */
    private Integer goodsStock;
    /** 秒杀价格 */
    private Double killPrice;
    /** 秒杀库存，-1表示没有限制 */
    private Integer killStock;
    /** 秒杀开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /** 秒杀结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Double getKillPrice() {
        return killPrice;
    }

    public void setKillPrice(Double killPrice) {
        this.killPrice = killPrice;
    }

    public Integer getKillStock() {
        return killStock;
    }

    public void setKillStock(Integer killStock) {
        this.killStock = killStock;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
