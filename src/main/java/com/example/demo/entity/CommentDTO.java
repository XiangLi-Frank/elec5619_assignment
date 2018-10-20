package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 08:59
 * @since 1.0.0
 */
@Data
@Table
@Entity
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String commodity;//关联商品id
    private String username;//用户名
    private String comment;//评论内容
    private Date date;//评论日期
}
