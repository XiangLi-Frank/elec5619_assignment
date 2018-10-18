package com.example.demo.repository;

import com.example.demo.entity.SellDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 09:17
 * @since 1.0.0
 */
public interface SellRepository extends JpaRepository<SellDTO,String> {
}
