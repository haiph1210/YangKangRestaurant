package com.haiph.salesservice.service.impl;

import com.haiph.salesservice.Enum.Status;
import com.haiph.salesservice.entity.BusinessManager;
import com.haiph.salesservice.entity.Payment;
import com.haiph.salesservice.exception.CommonException;
import com.haiph.salesservice.exception.Response;
import com.haiph.salesservice.repository.BusinessManagerRepository;
import com.haiph.salesservice.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessManagerServiceImpl implements com.haiph.salesservice.service.BusinessManagerService {
    @Autowired
    private BusinessManagerRepository businessManagerRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<BusinessManager> findAll(Pageable pageable) {
        try {
            Page<BusinessManager> page = businessManagerRepository.findAll(pageable);
            List<BusinessManager> findAllBA = page.getContent();
            return new PageImpl<>(findAllBA, pageable, page.getTotalElements());
        } catch (Exception exception) {
            throw new CommonException(Response.DATA_NOT_FOUND,"Cannot find All");
        }
    }

    @Override
    public void saveAllBMDay() {
        try {
            List<BusinessManager> findAllBA = businessManagerRepository.findAll();
            List<Payment> payments = paymentRepository.findAll();
            List<BusinessManager> businessManagers = new ArrayList<>();
            for (Payment payment : payments) {
                BusinessManager businessManager = new BusinessManager();
                if (checkExistDay(payment.getCreatedAt().toLocalDate())) {
                    if (checkStatus(payment)) {
                        businessManager.setDay(payment.getCreatedAt().toLocalDate());
                        businessManager.setTotalPayment(paymentRepository.countByCreatedAt(payment.getCreatedAt()));
                        businessManager.setMounth(payment.getCreatedAt().getMonthValue());
                        businessManager.setYear(payment.getCreatedAt().getYear());
                        businessManager.setTotalMoney(paymentRepository.totalMoneyForCreatedAt(payment.getCreatedAt()));
                        businessManagers.add(businessManager);
                        businessManagerRepository.saveAll(businessManagers);
                    }
                }
            }
        } catch (Exception exception) {
            throw new CommonException(Response.DATA_NOT_FOUND,"Cannot Save All");
        }
    }


    private boolean checkStatus(Payment payment) {
        if (payment.getStatus().equals(Status.SUCCESS)) {
            return true;
        }
        return false;
    }

    private boolean checkExistDay(LocalDate day) {
        if (!(businessManagerRepository.findById(day).isPresent())) {
            return true;
        }
        return false;
    }


    private LocalDate converstLocalDateTimeToLocalDate(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return localDateTime.toLocalDate();
    }

}
