package com.haiph.salesservice.repository;

import com.haiph.salesservice.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,Integer> {
    Discount findByDiscountCode(String Code);
}
