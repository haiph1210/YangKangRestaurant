package com.haiph.userservice.service;

import com.haiph.userservice.entity.bank.BankUser;
import com.haiph.userservice.model.request.banks.BankUserCreate;
import com.haiph.userservice.model.request.banks.BankUserUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankUserService {
    List<BankUser> findAll();

    Page<BankUser> findAllPage(Pageable pageable);

    BankUser findById(Integer id);

    BankUser create(BankUserCreate create);

    BankUser update(BankUserUpdate update);

    void deleteById(Integer id);
}
