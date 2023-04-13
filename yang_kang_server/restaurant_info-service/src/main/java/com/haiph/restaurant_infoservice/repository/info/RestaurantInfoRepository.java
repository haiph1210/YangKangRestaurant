package com.haiph.restaurant_infoservice.repository.info;

import com.haiph.restaurant_infoservice.entity.info.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo,Integer> {
    Optional<RestaurantInfo> findByRestaurantName(String name);
}
