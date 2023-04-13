package com.haiph.restaurantservice.repository;

import com.haiph.restaurantservice.entity.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo,Byte> {
    Optional<RestaurantInfo> findByName(String name);
}
