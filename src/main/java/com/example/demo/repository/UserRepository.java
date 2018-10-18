package com.example.demo.repository;

import com.example.demo.entity.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 09:14
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<UserDTO,String> {
}
