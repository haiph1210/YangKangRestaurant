package com.haiph.salesservice.service;

import com.haiph.salesservice.entity.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountService {
    Page<Discount> findAll(Pageable pageable);

    Discount findById(Integer id) throws Exception;

    Discount findByDiscountCode(String discountName) throws Exception;

    void create(Discount discount);
}
