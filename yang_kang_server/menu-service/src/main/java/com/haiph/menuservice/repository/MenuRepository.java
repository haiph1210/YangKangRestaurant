package com.haiph.menuservice.repository;

import com.haiph.menuservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
    Optional<Menu> findByPrice(Float price);
    Optional<Menu> findByName(String menuName);
}
