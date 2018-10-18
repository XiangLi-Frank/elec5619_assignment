package com.example.demo.controller;

import com.example.demo.entity.CommodityDTO;
import com.example.demo.enums.RestStatusEnum;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.CommodityRepository;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author qinzhongjian
 * @date created in 2018/10/18 12:49
 * @since 1.0.0
 */
@RestController
@RequestMapping("/commaodity")
public class CommaodityController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    CommodityRepository commodityRepository;

    @PostMapping(value="/upload")
    public RestEntity release(MultipartFile file) {
        try {
            String  fileName = this.fileUploadService.photoUpload(file);
            return RestEntity.ok(fileName);
        } catch (IOException e) {
            return RestEntity.error(RestStatusEnum.UPLOAD_IMAGE__ERROR,e);
        }

    }
    @GetMapping(value="/obtain/{username}/{pageSize}/{pageIndex}")
    public RestEntity obtain(@PathVariable String username, @PathVariable Integer pageSize, @PathVariable Integer pageIndex) {
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<CommodityDTO> commodityDTOS = this.commodityRepository.findAllByUsername(username,pageRequest);
        return RestEntity.ok(commodityDTOS);
    }

    @PostMapping(value="/add")
    public RestEntity add(@RequestBody CommodityDTO commodityDTO) {
        String id = commodityDTO.getId();//手动控制商品id
        CommodityDTO oldCommodityDTO = this.commodityRepository.findOne(id);//检查数据库是否存在商品
        if (oldCommodityDTO != null){
            return RestEntity.ok(RestStatusEnum.RELEASE_ERROR);
        }else {
            //商品不存在，进行新增
            this.commodityRepository.save(commodityDTO);
        }
        return RestEntity.ok(RestStatusEnum.RELEASE_SUCCESS,null,id);
    }
}
