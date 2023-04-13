package com.haiph.restaurantservice.service;

import com.haiph.restaurantservice.entity.RestaurantInfo;
import com.haiph.restaurantservice.repository.RestaurantInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantInfoService {
    @Autowired
    private RestaurantInfoRepository repository;
    @Autowired
    private ModelMapper mapper;

    public List<RestaurantInfo> findAll() {
        return repository.findAll();
    }

    public Optional<RestaurantInfo> findById(Byte id) {
        return repository.findById(id);
    }

    public Optional<RestaurantInfo> findByName(String name) {
        return repository.findByName(name);
    }

    public RestaurantInfo create(RestaurantInfo restaurantInfo) {
        return repository.save(restaurantInfo);
    }

    public RestaurantInfo update(RestaurantInfo restaurantInfo) {
        return repository.save(restaurantInfo);
    }

    public void deleteById(Byte id) {
        repository.deleteById(id);
    }

}
