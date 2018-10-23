package com.example.demo.controller;

import com.example.demo.entity.BuyDTO;
import com.example.demo.entity.CommodityDTO;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.BuyRepository;
import com.example.demo.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinzhongjian
 * @date created in 2018/10/19 10:42
 * @since 1.0.0
 */
@RestController
public class MallController {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    BuyRepository buyRepository;

    @GetMapping("/listCommodityDTO/{category}")
    public RestEntity listCommodityDTO(@PathVariable String category){
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAll();
        //获取库存不为0的商品
        commodityDTOS = commodityDTOS.stream().filter(commodityDTO -> commodityDTO.getLave() != 0)
                .filter(commodityDTO -> commodityDTO.getCategory().equals(category)).collect(Collectors.toList());
        return RestEntity.ok(commodityDTOS);
    }

    @PostMapping("/buyCommodityDTO")
    public RestEntity buyCommodityDTO(@RequestBody BuyDTO buyDTO){
        this.buyRepository.save(buyDTO);
        return RestEntity.ok(buyDTO);
    }

    /**
     * description: <br>
     * 买家订单信息
     * @param: [buyDTO]
     * @return: com.example.demo.model.RestEntity
     * @method：buyCommodityDTO
     */

    @GetMapping("/listBuyDTO/{username}/{pageSize}/{pageIndex}")
    public RestEntity listBuyDTO(@PathVariable String username, @PathVariable Integer pageSize, @PathVariable Integer pageIndex){
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAllByUsername(username,pageRequest);
        return RestEntity.ok(buyDTOS);
    }
}
