package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 09:09
 * @since 1.0.0
 */
@Data
@Table
@Entity
@IdClass(MultiKeysClass.class)
public class SellDTO {

    @Id
    private String id;//商品id，唯一标志号
    private String name;//商品名称
    private String username;//卖家
    private String picture;//图片
    private String content;//简介
    private Double price;//价格
    private Integer count;//购买数量
    private boolean state;//购买状态 0：提交订单  1：成功交易
}
