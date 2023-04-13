package com.haiph.menuservice.service;

import com.haiph.menuservice.entity.Menu;
import com.haiph.menuservice.model.request.MenuCreateRequest;
import com.haiph.menuservice.model.request.MenuUpdateRequest;
import com.haiph.menuservice.model.response.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    List<Menu> findAll();

    Page<Menu> findAll(Pageable pageable);

    Menu findById(Integer id);

    Optional<Menu> findByMenuName(String menuName);

    ResponseBody findByPrice(Float price);

    void create(MenuCreateRequest createRequest);

    void update(MenuUpdateRequest updateRequest);

    void deleteById(Integer id);
}
