package com.haiph.userservice.model.response.dto;

import com.haiph.userservice.entity.bank.BankUser;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BankUserDTO {
    private String bankUserNumber;
    private String userOwenrName;
    private BankUser.BankName bankName;
    private Double countPrice;
}
