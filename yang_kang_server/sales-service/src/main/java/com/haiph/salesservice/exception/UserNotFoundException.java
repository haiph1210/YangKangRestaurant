package com.haiph.salesservice.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{
    private final String msg;

    public UserNotFoundException(String msg) {
        this.msg = msg;
    }
}
