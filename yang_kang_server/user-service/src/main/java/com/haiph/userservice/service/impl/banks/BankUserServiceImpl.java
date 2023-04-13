package com.haiph.userservice.service.impl.banks;

import com.haiph.userservice.entity.bank.BankUser;
import com.haiph.userservice.model.request.banks.BankUserCreate;
import com.haiph.userservice.model.request.banks.BankUserUpdate;
import com.haiph.userservice.repository.bank.BankUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserServiceImpl implements com.haiph.userservice.service.BankUserService {
    @Autowired
    private BankUserRepository bankUserRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<BankUser> findAll() {
        return bankUserRepository.findAll();
    }
    public Page<BankUser> findDTO(Pageable pageable) {
        return bankUserRepository.findAll(pageable);
    }
    @Override
    public Page<BankUser> findAllPage(Pageable pageable) {
        return  bankUserRepository.findAll(pageable);
    }
    @Override
    public BankUser findById(Integer id) {
        return bankUserRepository.findById(id).orElse(null);
    }

    @Override
    public BankUser create(BankUserCreate create) {
        BankUser _new = mapper.map(create,BankUser.class);
        return bankUserRepository.save(_new);
    }

    @Override
    public BankUser update(BankUserUpdate update) {
        BankUser _update = mapper.map(update,BankUser.class);
        return bankUserRepository.save(_update);
    }

    @Override
    public void deleteById(Integer id) {
        bankUserRepository.deleteById(id);
    }
}
