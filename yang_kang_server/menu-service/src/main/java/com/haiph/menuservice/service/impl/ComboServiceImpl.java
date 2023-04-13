package com.haiph.menuservice.service.impl;

import com.haiph.menuservice.entity.Combo;
import com.haiph.menuservice.model.request.ComboCreate;
import com.haiph.menuservice.model.request.ComboUpdate;
import com.haiph.menuservice.repository.ComboRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboServiceImpl implements com.haiph.menuservice.service.ComboService {
    @Autowired
    private ComboRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Combo> findAll() {
        return repository.findAll();
    }
    @Override
    public Page<Combo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @Override
    public Combo findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public void create(ComboCreate create) {
        Combo combo = mapper.map(create,Combo.class);
        repository.save(combo);
    }
    @Override
    public void update(ComboUpdate update) {
        Combo combo = mapper.map(update,Combo.class);
        repository.save(combo);
    }
    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
