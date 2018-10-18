package com.example.demo.repository;

import com.example.demo.entity.CommentDTO;
import com.example.demo.entity.MultiKeysClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinzhongjian
 * @date created in 2018/10/17 09:18
 * @since 1.0.0
 */
public interface CommentRepository extends JpaRepository<CommentDTO, MultiKeysClass> {
}
