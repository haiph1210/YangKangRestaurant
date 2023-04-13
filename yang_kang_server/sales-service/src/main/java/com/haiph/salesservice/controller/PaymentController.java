package com.haiph.salesservice.controller;

import com.haiph.salesservice.entity.Payment;
import com.haiph.salesservice.model.request.PaymentCreate;
import com.haiph.salesservice.model.response.PaymentDTO;
import com.haiph.salesservice.service.impl.PaymentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/findPayment")
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/find-DTO-payment")
    public Page<PaymentDTO> findAllDTO(Pageable pageable) {
        return paymentService.findAllDTO(pageable);
    }

    @GetMapping("/findPaymentId/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping("/create-payment")
    public void create(@RequestBody PaymentCreate create) {
        paymentService.create(create);
    }

    @GetMapping("/total")
    public ResponseEntity totalMoney(@RequestParam LocalDateTime createdAt) {
       return ResponseEntity.ok(paymentService.totalMoneyForCreatedAt(createdAt)) ;
    }

    @GetMapping("/count")
    public ResponseEntity countCreatedAt(@RequestParam LocalDateTime createdAt) {
        return ResponseEntity.ok(paymentService.countForCreatedAt(createdAt)) ;
    }
}
