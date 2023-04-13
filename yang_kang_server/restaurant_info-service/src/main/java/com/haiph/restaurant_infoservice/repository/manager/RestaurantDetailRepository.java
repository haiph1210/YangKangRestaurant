package com.haiph.restaurant_infoservice.repository.manager;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantDetailRepository extends JpaRepository<RestaurantDetail,Integer> {
    Optional<RestaurantDetail> findByName(String name);

}
