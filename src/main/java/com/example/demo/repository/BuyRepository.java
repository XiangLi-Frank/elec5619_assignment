package com.example.demo.repository;

import com.example.demo.entity.BuyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyRepository extends JpaRepository<BuyDTO,Integer> {

    Page<BuyDTO> findAllByUsername(@Param("username")String username, Pageable pageable);

    Page<BuyDTO> findAllByStateAndCommodityIn(@Param("state")String state,@Param("commodity")List<String> commodity,Pageable pageable);
}
