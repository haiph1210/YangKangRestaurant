package com.haiph.restaurant_infoservice.repository.manager;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantMasterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantMasterialRepository extends JpaRepository<RestaurantMasterial,Integer> {
    Optional<RestaurantMasterial> findByName(String name);
}
