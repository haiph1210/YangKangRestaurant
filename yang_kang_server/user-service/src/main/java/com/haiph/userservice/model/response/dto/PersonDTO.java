package com.haiph.userservice.model.response.dto;

import com.haiph.userservice.entity.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String personCode;
    private String email;
    private String phoneNumber;
    private String address;
    private Person.Gender gender;
    private String fullName;
    private Person.Role role;
    private BankUserDTO bank;
}
