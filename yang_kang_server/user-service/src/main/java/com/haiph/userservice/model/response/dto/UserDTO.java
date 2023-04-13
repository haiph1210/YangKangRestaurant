package com.haiph.userservice.model.response.dto;

import com.haiph.userservice.entity.person.Person;
import com.haiph.userservice.entity.person.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String personCode;
    private String email;
    private String phoneNumber;
    private String address;
    private Person.Gender gender;
    private String fullName;
    private Person.Role role;
    private LocalDate createdDate;
    private String imgUrl;
    private User.UserType userType;
    private Integer countLogin;
    private BankUserDTO bank;
}
