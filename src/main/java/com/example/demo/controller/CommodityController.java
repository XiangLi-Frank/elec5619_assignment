package com.example.demo.controller;

import com.example.demo.repository.BuyRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CommodityRepository;
import com.example.demo.service.FileUploadService;

/**
 * @author haocun li
 * @date created in 2018/10/23 22:05
 * @since 1.0.0
 */
 
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BuyRepository buyRepository;
}