package com.haiph.salesservice.controller;

import com.haiph.salesservice.service.impl.BusinessManagerMounthServiceImpl;
import com.haiph.salesservice.service.impl.BusinessManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class BusinessManagerController {
    @Autowired
    private BusinessManagerServiceImpl businessManagerService;
    @Autowired
    private BusinessManagerMounthServiceImpl managerMounthService;
    @GetMapping("/findAllBMDay")
    public ResponseEntity findAllDay(Pageable pageable) {
        return ResponseEntity.ok(businessManagerService.findAll(pageable));
    }

    @GetMapping("/findAllBMMounth")
    public ResponseEntity findAllMount(Pageable pageable) {
        return ResponseEntity.ok(managerMounthService.findAll(pageable));
    }
}
