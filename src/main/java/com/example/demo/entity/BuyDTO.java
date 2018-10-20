package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 09:02
 * @since 1.0.0
 */
@Data
@Table
@Entity
public class BuyDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String commodity;//关联商品id
    private String name;//商品名称
    private String username;//买家
    private String picture;//图片
    private String content;//简介
    private Double price;//价格
    private Integer count;//购买数量
    private String state;//购买状态 order：提交订单  success：成功交易  fail：失败交易
}
