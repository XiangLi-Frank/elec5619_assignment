package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.BuyDTO;
import com.example.demo.entity.CommentDTO;
import com.example.demo.entity.CommodityDTO;
import com.example.demo.enums.RestStatusEnum;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.BuyRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CommodityRepository;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinzhongjian
 * @date created in 2018/10/18 12:49
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

    /**
     * description: <br>
     发布商品
     * @para: a
     * @eturn: a
     * @method：a
     */

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

    /**
     * description: <br>
     * 卖家获取订单信息
     * @para: a
     * @eturn: a
     * @method：a
     */

    @GetMapping("/listOrder/{username}/{pageSize}/{pageIndex}")
    public RestEntity listBuyDTO(@PathVariable String username,@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAllByUsername(username);
        List<String> ids = commodityDTOS.stream().map(commodityDTO -> commodityDTO.getId()).collect(Collectors.toList());
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAllByStateAndCommodityIn("order",ids,pageRequest);
        return RestEntity.ok(buyDTOS);
    }

    /**
     * description: <br>
     获取所有交易信息
     * @para: a
     * @eturn: a
     * @method：a
     */

    @GetMapping("/listOrder/{pageSize}/{pageIndex}")
    public RestEntity listAllBuyDTO(@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAll(pageRequest);
        return RestEntity.ok(buyDTOS);
    }
    @PostMapping("/okOrder/{id}/{commodity}/{count}")
    @Transactional
    public RestEntity okOrder(@PathVariable Integer id,@PathVariable String commodity,@PathVariable Integer count){
        //查询库存
        CommodityDTO commodityDTO = this.commodityRepository.findOne(commodity);
        Integer lave = commodityDTO.getLave();
        if (lave < count){
            return RestEntity.error(RestStatusEnum.INVENTORY_SHORTAGE,null);
        }
        //库存减少 更新入库
        commodityDTO.setSell(count);
        lave = lave-count;
        commodityDTO.setLave(lave);
        this.commodityRepository.save(commodityDTO);
        //改变购买状态
        BuyDTO buyDTO = this.buyRepository.findOne(id);
        buyDTO.setState("success");
        this.buyRepository.save(buyDTO);
        return RestEntity.ok(RestStatusEnum.OK);
    }
    @PostMapping("/cancleOrder/{id}")
    public RestEntity cancleOrder(@PathVariable Integer id){
        BuyDTO buyDTO = this.buyRepository.findOne(id);
        buyDTO.setState("fail");
        this.buyRepository.save(buyDTO);
        return RestEntity.ok(RestStatusEnum.OK);
    }
    /**
     * description: <br>
     *     商品详情
     * @para: a
     * @eturn: a
     * @method：a
     */

    @GetMapping("/getDetail/{id}")
    public RestEntity getDetail(@PathVariable String id){
        CommodityDTO commodityDTOS = this.commodityRepository.findOne(id);
        List<CommentDTO> commentDTOS = this.commentRepository.findAllByCommodity(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commodity",commodityDTOS);
        jsonObject.put("comment",commentDTOS);
        return RestEntity.ok(jsonObject);
    }


}
