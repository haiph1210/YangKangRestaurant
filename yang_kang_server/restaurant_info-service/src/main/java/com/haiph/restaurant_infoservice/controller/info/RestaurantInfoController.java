package com.haiph.restaurant_infoservice.controller.info;


import com.haiph.restaurant_infoservice.entity.info.RestaurantInfo;
import com.haiph.restaurant_infoservice.service.impl.info.RestaurantInfoService;
import org.apache.http.protocol.HTTP;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.haiph.restaurant_infoservice.model.response.ResponseBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantInfoController {
    @Autowired
    private RestaurantInfoService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping("/findInfo")
    public List<RestaurantInfo> findAll() {
        return service.findAll();
    }

    @GetMapping("/findInfoId/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        Optional<RestaurantInfo> finRIById= service.findById(id);
        if (finRIById.isPresent()) return ResponseEntity.ok(finRIById);
        return ResponseEntity.badRequest().body("cannot find id :" + id);
    }
    @GetMapping("/findInfoName/{name}")
    public ResponseBody findByName(@PathVariable String name) {
        Optional<RestaurantInfo> finRIByName= service.findByName(name);
        if (finRIByName.isEmpty()) return new ResponseBody(ResponseBody._400,"can not find name: " + name);
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,finRIByName);
    }
    @PostMapping("/create-info")
    public ResponseBody create(@RequestBody RestaurantInfo restaurantInfo) {
            service.create(restaurantInfo);
            return new ResponseBody(ResponseBody._200,ResponseBody.CREATE_SUCESS);
    }
    @PutMapping("/update-info/{id}")
    public ResponseBody update(@PathVariable Integer id,@RequestBody RestaurantInfo restaurantInfo) {
        restaurantInfo.setId(id);
        Optional<RestaurantInfo> findId = service.findById(id);
        if (findId.isEmpty()) return new ResponseBody(ResponseBody._400,ResponseBody.UPDATE_FAIL);
        service.update(restaurantInfo);
        return new ResponseBody(ResponseBody._200,ResponseBody.UPDATE_SUCESS);
    }
    @DeleteMapping("/delete-info/{id}")
    public ResponseBody deleteById(@PathVariable Integer id) {
        Optional<RestaurantInfo> findId = service.findById(id);
        if (findId.isEmpty()) return new ResponseBody(ResponseBody._400,"Can not delete with id : " +id + "is not find!" );
        service.deleteById(id);
        return new ResponseBody(ResponseBody._200,ResponseBody.DELETE_SUCESS);
    }
}
