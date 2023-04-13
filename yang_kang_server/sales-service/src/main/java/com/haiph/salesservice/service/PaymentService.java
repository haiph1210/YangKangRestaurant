package com.haiph.salesservice.service;

import com.haiph.salesservice.entity.Payment;
import com.haiph.salesservice.model.request.PaymentCreate;
import com.haiph.salesservice.model.request.PaymentUpdate;
import com.haiph.salesservice.model.response.PaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    PaymentDTO findById(Integer id);

    void create(PaymentCreate create);

    List<Payment> findAll();

    Page<PaymentDTO> findAllDTO(Pageable pageable);

    void update(PaymentUpdate update);

    Double totalMoneyForCreatedAt(LocalDateTime localDateTime);

    Integer countForCreatedAt(LocalDateTime localDateTime);
}
