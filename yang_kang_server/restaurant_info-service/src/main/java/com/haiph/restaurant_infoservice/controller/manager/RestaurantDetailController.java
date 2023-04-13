package com.haiph.restaurant_infoservice.controller.manager;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantDetail;
import com.haiph.restaurant_infoservice.model.request.detail.RestaurantDetailCreate;
import com.haiph.restaurant_infoservice.model.request.detail.RestaurantDetailUpdate;
import com.haiph.restaurant_infoservice.model.response.ResponseBody;
import com.haiph.restaurant_infoservice.model.response.manager.RestaurantDetailDTO;
import com.haiph.restaurant_infoservice.service.impl.manager.RestaurantDetailService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant") //"detail"
public class RestaurantDetailController {
    @Autowired
    private RestaurantDetailService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/findDetail")
    public List<RestaurantDetail> findAll() {
        return service.findAll();
    }
    @GetMapping("/find-DTO-detail")
    public Page<RestaurantDetailDTO> findAll(Pageable pageable) {
        Page<RestaurantDetail> page = service.findAll(pageable);
        List<RestaurantDetail> list = page.getContent();
        List<RestaurantDetailDTO> detailDTOS = mapper.map(list,new TypeToken<List<RestaurantDetailDTO>>(){}.getType());
        return new PageImpl<>(detailDTOS,pageable,page.getTotalElements());
    }
    @GetMapping("/findDetailId/{id}")
    public ResponseBody findById(@PathVariable Integer id) {
        Optional<RestaurantDetail> findId = service.findById(id);
        if (findId == null) return new ResponseBody(ResponseBody._400,ResponseBody.FAIL);
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,mapper.map(findId,RestaurantDetail.class));
    }
    @GetMapping("/findDetailName/{name}")
    public ResponseBody findByName(@PathVariable String name) {
        Optional<RestaurantDetail> findByName = service.findByName(name);
        if (findByName == null) return new ResponseBody(ResponseBody._400,"can not find name :" + name);
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,mapper.map(findByName,RestaurantDetail.class));
    }
    @PostMapping("/create-detail")
    public void create(@RequestBody RestaurantDetailCreate create) {
        service.create(create);
    }
    @PutMapping("/update-detail/{id}")
    public ResponseBody update(@PathVariable Integer id, @RequestBody RestaurantDetailUpdate update) {
        update.setId(id);
        Optional<RestaurantDetail> findId = service.findById(id);
        if (findId == null) return new ResponseBody(ResponseBody._400,ResponseBody.FAIL);
        service.update(update);
        return new ResponseBody(ResponseBody._200,ResponseBody.OK);
    }
    @DeleteMapping("/delete-detail/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
