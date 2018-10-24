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
	
	@PostMapping(value="/upload")
	public RestEntity release(MultipartFile file) {
	}
	
    @GetMapping(value="/obtain/{username}/{pageSize}/{pageIndex}")
    public RestEntity obtain(@PathVariable String username, @PathVariable Integer pageSize, @PathVariable Integer pageIndex) {
	}
	
    @PostMapping(value="/add")
    public RestEntity add(@RequestBody CommodityDTO commodityDTO) {
	}
	
    @GetMapping("/listOrder/{username}/{pageSize}/{pageIndex}")
    public RestEntity listBuyDTO(@PathVariable String username,@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
	}
	
	@GetMapping("/listOrder/{pageSize}/{pageIndex}")
    public RestEntity listAllBuyDTO(@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
	}
	
	@PostMapping("/okOrder/{id}/{commodity}/{count}")
    @Transactional
    public RestEntity okOrder(@PathVariable Integer id,@PathVariable String commodity,@PathVariable Integer count){
	}
	
	@PostMapping("/cancleOrder/{id}")
    public RestEntity cancleOrder(@PathVariable Integer id){
	}
	
	@GetMapping("/getDetail/{id}")
    public RestEntity getDetail(@PathVariable String id){
	}
	 
}