package com.example.demo.controller;

import com.example.demo.entity.CommentDTO;
import com.example.demo.enums.RestStatusEnum;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Fulina
 * @date created in 2018/10/19 14:12
 * @since 1.0.0
 */
@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/addComment")
    public RestEntity addComment(@RequestBody CommentDTO commentDTO){
        commentDTO.setDate(new Date());
        this.commentRepository.save(commentDTO);
        return RestEntity.ok(RestStatusEnum.OK);
    }
}
