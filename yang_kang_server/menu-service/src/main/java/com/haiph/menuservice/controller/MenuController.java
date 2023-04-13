package com.haiph.menuservice.controller;


import com.haiph.menuservice.entity.Menu;
import com.haiph.menuservice.model.request.MenuCreateRequest;
import com.haiph.menuservice.model.request.MenuUpdateRequest;
import com.haiph.menuservice.model.response.MenuDTO;
import com.haiph.menuservice.model.response.ResponseBody;
import com.haiph.menuservice.service.impl.MenuServiceImpl;
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
@RequestMapping(value = "/menu")
public class MenuController {
    @Autowired
    private MenuServiceImpl service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/find-menu")
    public List<Menu> findAll() {
        return service.findAll();
    }

    @GetMapping("/find-dto-menu")
    public Page<MenuDTO> findAll(Pageable pageable) {
        Page<Menu> page = service.findAll(pageable);
        List<Menu> comboList = page.getContent();
        List<MenuDTO> menuDTOS = mapper.map(comboList, new TypeToken<List<MenuDTO>>() {
        }.getType());
        return new PageImpl<>(menuDTOS, pageable, page.getTotalElements());
    }

    @GetMapping("/findMenuId/{id}")
    public MenuDTO findById(@PathVariable Integer id) {
        Menu menu = service.findById(id);
        return mapper.map(menu, MenuDTO.class);

    }

    @GetMapping("/findMenuName/{name}")
    public ResponseBody findById(@PathVariable String name) {
        Optional<Menu> menu = service.findByMenuName(name);
        if (menu == null) return new ResponseBody(ResponseBody._400, ResponseBody.FAIL);
        return new ResponseBody(ResponseBody._200, ResponseBody.OK, mapper.map(menu, MenuDTO.class));

    }

    @PostMapping("/create-menu")
    public void create(@RequestBody MenuCreateRequest menuCreateRequest) {
        service.create(menuCreateRequest);
    }

    @PutMapping("update-menu/{id}")
    public ResponseBody update(@PathVariable Integer id, @RequestBody MenuUpdateRequest menuUpdateRequest) {
        menuUpdateRequest.setId(id);
        Menu menu1 = service.findById(id);
        if (menu1 == null) return new ResponseBody(ResponseBody._400, ResponseBody.NOT_FIND_ID);
        service.update(menuUpdateRequest);
        return new ResponseBody(ResponseBody._200, ResponseBody.UPDATE_SUCESS);
    }

    @DeleteMapping("delete-menu/{id}")
    public ResponseBody deleteById(@PathVariable Integer id) {
        Menu menu1 = service.findById(id);
        if (menu1 == null) return new ResponseBody(ResponseBody._400, "can not find Id: " + id);
        service.deleteById(id);
        return new ResponseBody(ResponseBody._200, ResponseBody.DELETE_SUCESS);
    }
}
