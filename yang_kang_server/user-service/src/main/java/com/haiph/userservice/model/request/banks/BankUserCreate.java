package com.haiph.userservice.model.request.banks;

import com.haiph.userservice.entity.bank.BankUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankUserCreate {
    private String name;
    private String bankUserNumber; // số tài khoản
    private String userOwenrName; // tên chủ tài khoản
    private BankUser.BankName bankName; // tên ngân hàng
    private String PersonCode;
}
