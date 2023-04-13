package com.haiph.salesservice.exception;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("Access Denied");
    }
}
