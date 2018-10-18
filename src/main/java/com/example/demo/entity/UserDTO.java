package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 08:39
 * @since 1.0.0
 */
@Data
@Table
@Entity
public class UserDTO {
    @Id
    private String username;//用户名
    private String password;//密码
    private String role;//角色
}
