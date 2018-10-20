package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 08:45
 * @since 1.0.0
 */
@Data
@Table
@Entity
public class CommodityDTO {
    @Id
    private String id;//商品编号
    private String name;//商品名称
    private String username;//商家
    private String picture;//图片
    private String introduction;//简介
    private Double price;//价格
    private Integer lave;//剩余件数
    private Integer sell;//售出件数

}
