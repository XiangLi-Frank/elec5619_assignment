package com.example.demo.controller;

import com.example.demo.entity.CommodityDTO;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.BuyRepository;
import com.example.demo.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haocunli
 * @date created in 2018/10/24 10:42
 * @since 1.0.0
 */

public class MallController {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    BuyRepository buyRepository;

    @GetMapping("/listCommodityDTO/{category}")
    public RestEntity listCommodityDTO(@PathVariable String category){
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAll();
        //get commodity that its stock is not empty
        commodityDTOS = commodityDTOS.stream().filter(commodityDTO -> commodityDTO.getLave() != 0)
                .filter(commodityDTO -> commodityDTO.getCategory().equals(category)).collect(Collectors.toList());
        return RestEntity.ok(commodityDTOS);
    }
	
	    @GetMapping("/listCommodityDTO/{category}/{keyword}")
    public RestEntity listCommodityDTO(@PathVariable String category,@PathVariable String keyword){
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAll();
        commodityDTOS = commodityDTOS.stream().filter(commodityDTO -> commodityDTO.getLave() != 0)
                .filter(commodityDTO -> commodityDTO.getCategory().equals(category))
                .filter(commodityDTO -> commodityDTO.getName().indexOf(keyword) != -1).collect(Collectors.toList());
        return RestEntity.ok(commodityDTOS);
    }
}
