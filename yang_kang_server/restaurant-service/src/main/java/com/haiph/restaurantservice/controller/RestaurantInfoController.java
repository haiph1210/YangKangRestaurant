package com.haiph.restaurantservice.controller;

import com.haiph.restaurantservice.entity.RestaurantInfo;
import com.haiph.restaurantservice.model.response.ResponseBody;
import com.haiph.restaurantservice.service.RestaurantInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantInfo")
public class RestaurantInfoController {
    @Autowired
    private RestaurantInfoService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping
    public List<RestaurantInfo> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseBody findById(@PathVariable Byte id) {
        Optional<RestaurantInfo> finRIById= service.findById(id);
        if (finRIById.isEmpty()) return new ResponseBody(ResponseBody._400,"can not find id: " + id);
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,finRIById);
    }
    @GetMapping("/name/{name}")
    public ResponseBody findByName(@PathVariable String name) {
        Optional<RestaurantInfo> finRIByName= service.findByName(name);
        if (finRIByName.isEmpty()) return new ResponseBody(ResponseBody._400,"can not find name: " + name);
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,finRIByName);
    }
    @PostMapping
    public ResponseBody create(@RequestBody RestaurantInfo restaurantInfo) {
            service.create(restaurantInfo);
            return new ResponseBody(ResponseBody._200,ResponseBody.CREATE_SUCESS);
    }
    @PutMapping
    public ResponseBody update(@PathVariable Byte id,@RequestBody RestaurantInfo restaurantInfo) {
        Optional<RestaurantInfo> findId = service.findById(id);
        if (findId.isEmpty()) return new ResponseBody(ResponseBody._400,ResponseBody.UPDATE_FAIL);
        service.update(restaurantInfo);
        return new ResponseBody(ResponseBody._200,ResponseBody.UPDATE_SUCESS);
    }
    @DeleteMapping
    public ResponseBody deleteById(@PathVariable Byte id) {
        Optional<RestaurantInfo> findId = service.findById(id);
        if (findId.isEmpty()) return new ResponseBody(ResponseBody._400,"Can not delete with id : " +id + "is not find!" );
        service.deleteById(id);
        return new ResponseBody(ResponseBody._200,ResponseBody.DELETE_SUCESS);
    }
}
