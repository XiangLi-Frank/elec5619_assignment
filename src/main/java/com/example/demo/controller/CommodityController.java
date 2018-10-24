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
        String id = commodityDTO.getId();//id of commodity
        CommodityDTO oldCommodityDTO = this.commodityRepository.findOne(id);//check whether the commodity exists or not
        if (oldCommodityDTO != null){
            return RestEntity.ok(RestStatusEnum.RELEASE_ERROR);
        }else {
            //The commodity is not existing, add it
            this.commodityRepository.save(commodityDTO);
        }
        return RestEntity.ok(RestStatusEnum.RELEASE_SUCCESS,null,id);
	}
	
    @GetMapping("/listOrder/{username}/{pageSize}/{pageIndex}")
    public RestEntity listBuyDTO(@PathVariable String username,@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAllByUsername(username);
        List<String> ids = commodityDTOS.stream().map(commodityDTO -> commodityDTO.getId()).collect(Collectors.toList());
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAllByStateAndCommodityIn("order",ids,pageRequest);
        return RestEntity.ok(buyDTOS);
	}
	
	@GetMapping("/listOrder/{pageSize}/{pageIndex}")
    public RestEntity listAllBuyDTO(@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAll(pageRequest);
        return RestEntity.ok(buyDTOS);
	}
	
	@PostMapping("/okOrder/{id}/{commodity}/{count}")
    @Transactional
    public RestEntity okOrder(@PathVariable Integer id,@PathVariable String commodity,@PathVariable Integer count){
        //check stock
        CommodityDTO commodityDTO = this.commodityRepository.findOne(commodity);
        Integer lave = commodityDTO.getLave();
        if (lave < count){
            return RestEntity.error(RestStatusEnum.INVENTORY_SHORTAGE,null);
        }
        //stock - 1, add to database
        commodityDTO.setSell(count);
        lave = lave-count;
        commodityDTO.setLave(lave);
        this.commodityRepository.save(commodityDTO);
        //change buy state
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