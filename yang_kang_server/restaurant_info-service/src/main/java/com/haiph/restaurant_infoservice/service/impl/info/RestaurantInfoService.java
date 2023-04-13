package com.haiph.restaurant_infoservice.service.impl.info;


import com.haiph.restaurant_infoservice.entity.info.RestaurantInfo;
import com.haiph.restaurant_infoservice.repository.info.RestaurantInfoRepository;
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

    public Optional<RestaurantInfo> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<RestaurantInfo> findByName(String name) {
        return repository.findByRestaurantName(name);
    }

    public RestaurantInfo create(RestaurantInfo restaurantInfo) {
        return repository.save(restaurantInfo);
    }

    public RestaurantInfo update(RestaurantInfo restaurantInfo) {
        return repository.save(restaurantInfo);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
