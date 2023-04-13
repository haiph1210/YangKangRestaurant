package com.haiph.userservice.model.request.employee;

import com.haiph.userservice.entity.bank.BankUser;
import com.haiph.userservice.entity.person.Person;
import com.haiph.userservice.entity.person.Employee;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EmployeeCreate {
    @NotNull(message = "username shouldn't be null")
    private String username;
    @NotNull(message = "username shouldn't be null")
    private String password;
    @Email(message = "email invalid")
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Invalid phone number")
    private String phoneNumber;
    private String address;
    @Pattern(regexp = "^(MALE|FEMALE)$", message = "Invalid gender")
    private Person.Gender gender;
    @Pattern(regexp = "^(ACTIVE|NOT_ACTIVE)$", message = "Invalid status")
    private Person.Status status;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^(USER|ADMIN|EMPLOYEE)$", message = "Invalid role")
    private Person.Role role;
    private LocalDate createdDate;
    @Pattern(regexp = "^(SERVE|CHEF|MANAGER|SERVICE)$", message = "Invalid position")
    private Employee.Position position;
    private LocalDate createAt;
    private String cmnd;
    protected List<BankUserDTO> bankDTOS;

    @Getter
    @Setter
    public static class BankUserDTO {
        private Integer id;
        private String name;
        private String bankUserNumber; // số tài khoản
        private String userOwenrName; // tên chủ tài khoản
        private BankUser.BankName bankName; // tên ngân hàng
    }


}
