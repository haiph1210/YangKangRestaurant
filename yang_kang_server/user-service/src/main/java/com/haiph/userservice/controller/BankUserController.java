package com.haiph.userservice.controller;

import com.haiph.salesservice.exception.Response;
import com.haiph.salesservice.exception.ResponseBody;
import com.haiph.userservice.entity.bank.BankUser;
import com.haiph.userservice.model.request.SendMail;
import com.haiph.userservice.model.request.banks.BankUserCreate;
import com.haiph.userservice.model.request.banks.BankUserUpdate;
import com.haiph.userservice.model.response.dto.BankUserDTO;
import com.haiph.userservice.repository.person.PersonRepository;
import com.haiph.userservice.service.BankUserService;
import com.haiph.userservice.service.impl.sercurity.EmailService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class BankUserController {
    @Autowired
    private BankUserService bankUserService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmailService emailService;

    @GetMapping("/findBank")
    public ResponseEntity<ResponseBody> findAll() {
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        bankUserService.findAll()
                ));
    }

    @GetMapping("/find-DTO-bank")
    public ResponseEntity<ResponseBody>  findAllDTO(Pageable pageable) {
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        bankUserService.findAllPage(pageable)
                ));
    }
    @GetMapping("/findBankId/{id}")
    public ResponseEntity<ResponseBody> findBankById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        bankUserService.findById(id)
                ));
    }
    @PostMapping("/{id}/createBank")
    public ResponseEntity<ResponseBody> createBank(@RequestBody BankUserCreate bankUserCreate) {
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        bankUserService.create(bankUserCreate)
                ));

    }
    @PutMapping("/update-bank")
    public ResponseEntity<ResponseBody> updateBank(@PathVariable Integer id,@RequestBody BankUserUpdate bankUserUpdate) {
        bankUserUpdate.setId(id);
        BankUser update = bankUserService.update(bankUserUpdate);
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage(),
                        update
                ));
    }
    @DeleteMapping("delete-bank")
    public ResponseEntity<ResponseBody> deleteBankById(@PathVariable Integer id) {
        bankUserService.deleteById(id);
        return ResponseEntity.ok(
                new ResponseBody(
                        Response.SUCCESS.getResponseCode(),
                        Response.SUCCESS.getResponseMessage()
                ));
    }

    @PostMapping("/sendMail")
    public ResponseEntity<ResponseBody> sendMail(@RequestBody SendMail sendMail) {

        return ResponseEntity.ok(new ResponseBody(Response.SUCCESS,emailService.sendMail2(sendMail.getEmailRevice(),sendMail.getSubject(),sendMail.getMessage())));
    }
}
