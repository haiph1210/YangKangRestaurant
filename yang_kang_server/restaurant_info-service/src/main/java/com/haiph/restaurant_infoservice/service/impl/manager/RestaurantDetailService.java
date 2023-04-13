package com.haiph.restaurant_infoservice.service.impl.manager;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantDetail;
import com.haiph.restaurant_infoservice.model.request.detail.RestaurantDetailCreate;
import com.haiph.restaurant_infoservice.model.request.detail.RestaurantDetailUpdate;
import com.haiph.restaurant_infoservice.model.response.manager.RestaurantDetailDTO;
import com.haiph.restaurant_infoservice.repository.manager.RestaurantDetailRepository;
import jakarta.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantDetailService {
    @Autowired
    private RestaurantDetailRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RestTemplate restTemplate;

    public List<RestaurantDetail> findAll() {
      return repository.findAll();
    }
    public Page<RestaurantDetail> findAll(Pageable pageable) {
       return repository.findAll(pageable);
    }

    public Optional<RestaurantDetail>  findById(Integer id) {
        return repository.findById(id);

    }

    public Optional<RestaurantDetail> findByName(String name) {
        Optional<RestaurantDetail> findName = repository.findByName(name);
        return findName;
    }

    public void create(RestaurantDetailCreate create) {
        RestaurantDetail detail = mapper.map(create, RestaurantDetail.class);
         repository.save(detail);
    }

    public RestaurantDetail update(RestaurantDetailUpdate update) {
        RestaurantDetail detail = mapper.map(update, RestaurantDetail.class);
        return repository.save(detail);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
