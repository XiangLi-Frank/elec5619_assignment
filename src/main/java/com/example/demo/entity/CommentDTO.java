package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 08:59
 * @since 1.0.0
 */
@Data
@Table
@Entity
@IdClass(MultiKeysClass.class)
public class CommentDTO {
    @Id
    private String id;//商品id
    @Id
    private String username;//用户名
    private String comment;//评论内容
    private Date date;//评论日期
}
