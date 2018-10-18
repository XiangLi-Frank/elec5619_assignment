package com.example.demo.repository;

import com.example.demo.entity.BuyDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 09:26
 * @since 1.0.0
 */
public interface BuyRepository extends JpaRepository<BuyDTO,String> {
}
