package com.haiph.userservice.model.response.sercurity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TokenResponse {
    private String token;
    private String username;
    private String role;
}
