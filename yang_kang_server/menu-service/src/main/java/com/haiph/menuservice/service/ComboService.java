package com.haiph.menuservice.service;

import com.haiph.menuservice.entity.Combo;
import com.haiph.menuservice.model.request.ComboCreate;
import com.haiph.menuservice.model.request.ComboUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComboService {
    List<Combo> findAll();

    Page<Combo> findAll(Pageable pageable);

    Combo findById(Integer id);

    void create(ComboCreate create);

    void update(ComboUpdate update);

    void deleteById(Integer id);
}
