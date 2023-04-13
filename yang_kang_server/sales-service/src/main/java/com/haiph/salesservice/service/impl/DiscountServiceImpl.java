package com.haiph.salesservice.service.impl;

import com.haiph.salesservice.entity.Discount;
import com.haiph.salesservice.exception.CommonException;
import com.haiph.salesservice.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements com.haiph.salesservice.service.DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Page<Discount> findAll(Pageable pageable) {
        return discountRepository.findAll(pageable);
    }

    @Override
    public Discount findById(Integer id) throws Exception {
        Discount findDiscount = discountRepository.findById(id).orElse(null);
        try {
            if (findDiscount != null) {
                return findDiscount;
            }
        }catch (Exception exception) {
            throw new Exception("Cannot find Id:" +id);
        }
    return findDiscount;
    }

    @Override
    public Discount findByDiscountCode(String discountName) throws Exception {
        Discount findDiscount = discountRepository.findByDiscountCode(discountName);
        try {
            if (findDiscount != null) {
                return findDiscount;
            }
        }catch (Exception exception) {
            throw new Exception("Cannot find Id:" +discountName);
        }
        return findDiscount;
    }

    @Override
    public void create(Discount discount) {
        discountRepository.save(discount);
    }
}


