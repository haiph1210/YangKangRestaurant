package com.haiph.userservice.model.request.user;

import com.haiph.userservice.entity.person.Person;
import com.haiph.userservice.entity.person.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class UserCreate {
    private String personCode;
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
    private String imgUrl;
    private User.UserType userType;
    private Integer countLogin;
    private List<BankUser> bankUsers;
    @Getter
    @Setter
    public static class BankUser {
        private String name;
        private String bankUserNumber; // số tài khoản
        private String userOwenrName; // tên chủ tài khoản
        private com.haiph.userservice.entity.bank.BankUser.BankName bankName; // tên ngân hàng
    }

}
