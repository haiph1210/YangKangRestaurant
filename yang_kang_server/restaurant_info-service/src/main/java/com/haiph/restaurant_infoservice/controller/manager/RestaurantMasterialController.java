package com.haiph.restaurant_infoservice.controller.manager;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantDetail;
import com.haiph.restaurant_infoservice.entity.manager.RestaurantMasterial;
import com.haiph.restaurant_infoservice.model.request.masterial.RestaurantMasterialCreate;
import com.haiph.restaurant_infoservice.model.request.masterial.RestaurantMasterialUpdate;
import com.haiph.restaurant_infoservice.model.response.ResponseBody;
import com.haiph.restaurant_infoservice.model.response.manager.RestaurantDetailDTO;
import com.haiph.restaurant_infoservice.model.response.manager.RestaurantMasterialDTO;
import com.haiph.restaurant_infoservice.service.impl.manager.RestaurantMasterialService;
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
@RequestMapping("/restaurant") //"masterial"
public class RestaurantMasterialController {
    @Autowired
    private RestaurantMasterialService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/findMasterial")
    public List<RestaurantMasterial> findAll() {
        return service.findAll();
    }
    @GetMapping("/find-DTO-masterial")
    public Page<RestaurantMasterialDTO> findAll(Pageable pageable) {
        Page<RestaurantMasterial> page = service.findAll(pageable);
        List<RestaurantMasterial> list = page.getContent();
        List<RestaurantMasterialDTO> detailDTOS = mapper.map(list,new TypeToken<List<RestaurantMasterialDTO>>(){}.getType());
        return new PageImpl<>(detailDTOS,pageable,page.getTotalElements());
    }
    @GetMapping("/findMasterialId/{id}")
    public ResponseBody findById(@PathVariable Integer id) {
        Optional<RestaurantMasterial> findId = service.findById(id);
        if (findId == null) return new ResponseBody(ResponseBody._400, ResponseBody.FAIL);
        return new ResponseBody(ResponseBody._200, ResponseBody.OK, mapper.map(findId, RestaurantMasterial.class));
    }

    @GetMapping("/findMasterialName/{name}")
    public ResponseBody findByName(@PathVariable String name) {
        Optional<RestaurantMasterial> findByName = service.findByName(name);
        if (findByName == null) return new ResponseBody(ResponseBody._400, "can not find name :" + name);
        return new ResponseBody(ResponseBody._200, ResponseBody.OK, mapper.map(findByName, RestaurantDetail.class));
    }

    @PostMapping("/create-masterial")
    public ResponseBody create(@RequestBody RestaurantMasterialCreate create) {
        service.create(create);
        return new ResponseBody(ResponseBody._200, ResponseBody.CREATE_SUCESS);
    }

    @PutMapping("/update-masterial/{id}")
    public ResponseBody update(@PathVariable Integer id, @RequestBody RestaurantMasterialUpdate update) {
        update.setId(id);
        Optional<RestaurantMasterial> findId = service.findById(id);
        if (findId == null) return new ResponseBody(ResponseBody._400, ResponseBody.FAIL);
        service.update(update);
        return new ResponseBody(ResponseBody._200, ResponseBody.OK);
    }

    @DeleteMapping("/delete-masterial/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
