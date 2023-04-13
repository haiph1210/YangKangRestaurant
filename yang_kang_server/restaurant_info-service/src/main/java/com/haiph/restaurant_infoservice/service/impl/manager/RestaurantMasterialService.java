package com.haiph.restaurant_infoservice.service.impl.manager;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantDetail;
import com.haiph.restaurant_infoservice.entity.manager.RestaurantMasterial;
import com.haiph.restaurant_infoservice.model.request.masterial.RestaurantMasterialCreate;
import com.haiph.restaurant_infoservice.model.request.masterial.RestaurantMasterialUpdate;
import com.haiph.restaurant_infoservice.repository.manager.RestaurantDetailRepository;
import com.haiph.restaurant_infoservice.repository.manager.RestaurantMasterialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantMasterialService {
    @Autowired
    private RestaurantMasterialRepository repository;
    @Autowired
    private RestaurantDetailRepository restaurantDetailRepository;
    @Autowired
    private ModelMapper mapper;

    public List<RestaurantMasterial> findAll() {
        return repository.findAll();
    }
    public Page<RestaurantMasterial> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Optional<RestaurantMasterial> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<RestaurantMasterial> findByName(String name) {
        return repository.findByName(name);
    }

    public void create(RestaurantMasterialCreate create) {
        RestaurantMasterial masterialCreate = mapper.map(create, RestaurantMasterial.class);
        repository.save(masterialCreate);

    }

    public RestaurantMasterial update(RestaurantMasterialUpdate update) {
        RestaurantMasterial masterialUpdate = mapper.map(update, RestaurantMasterial.class);
        return repository.save( masterialUpdate);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
