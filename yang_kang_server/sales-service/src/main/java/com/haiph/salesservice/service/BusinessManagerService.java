package com.haiph.salesservice.service;

import com.haiph.salesservice.entity.BusinessManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessManagerService {
    Page<BusinessManager> findAll(Pageable pageable);

    void saveAllBMDay();
}
