package com.haiph.userservice.model.response.dto;

import com.haiph.userservice.entity.person.Person;
import com.haiph.userservice.entity.person.Employee;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EmployeeDTO {
    private String personCode;
    private String email;
    private String phoneNumber;
    private String address;
    private Person.Gender gender;
    private String fullName;
    private Person.Role role;
    private LocalDate createdDate;
    private Employee.Position position;
    private Float salary;
    private BankUserDTO bank;
}
