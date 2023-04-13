package com.haiph.userservice.model.request.sercurity;


import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRequest {
    @NotNull(message = "username shoutn't be null")
    private String username;
    @NotNull(message = "password shoutn't be null")
    private String password;
}
