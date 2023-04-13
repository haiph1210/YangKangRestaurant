package com.haiph.salesservice.service;

import com.haiph.salesservice.entity.BusinessManagerMouth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessManagerMounthService {
    void saveAllBMMounth();

    Page<BusinessManagerMouth> findAll(Pageable pageable);
}
