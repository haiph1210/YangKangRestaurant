package com.haiph.menuservice.service.impl;

import com.haiph.menuservice.entity.Menu;
import com.haiph.menuservice.model.request.MenuCreateRequest;
import com.haiph.menuservice.model.request.MenuUpdateRequest;
import com.haiph.menuservice.model.response.ResponseBody;
import com.haiph.menuservice.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements com.haiph.menuservice.service.MenuService {
    @Autowired
    private MenuRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<Menu> findAll(){
        return  repository.findAll();
    }
    @Override
    public Page<Menu> findAll(Pageable pageable){
        return  repository.findAll(pageable);
    }

    @Override
    public Menu findById(Integer id){
       return repository.findById(id).orElse(null);

    }

    @Override
    public Optional<Menu> findByMenuName(String menuName){
        Optional<Menu> menu = repository.findByName(menuName);
       return menu;
    }

    @Override
    public ResponseBody findByPrice(Float price){
        Optional<Menu> menu = repository.findByPrice(price);
        if (menu == null) return new ResponseBody(ResponseBody._400,ResponseBody.FAIL);
        return new ResponseBody(ResponseBody._200,ResponseBody.OK,menu);
    }

    @Override
    public void create(MenuCreateRequest createRequest) {
        Menu menu = mapper.map(createRequest,Menu.class);
        repository.save(menu);
    }
    @Override
    public void update(MenuUpdateRequest updateRequest) {
        Menu menu = mapper.map(updateRequest,Menu.class);
        repository.save(menu);
    }


    @Override
    public void deleteById(Integer id) {
         repository.deleteById(id);
    }
}


