package com.haiph.userservice.model.request.banks;

import com.haiph.userservice.entity.bank.BankUser;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class BankUserUpdate {
    private Integer id;
    private String name;
    private String bankUserNumber; // số tài khoản
    private String userOwenrName; // tên chủ tài khoản
    private BankUser.BankName bankName; // tên ngân hàng
    private String personCode;
}
