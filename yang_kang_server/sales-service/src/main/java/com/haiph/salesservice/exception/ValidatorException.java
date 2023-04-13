package com.haiph.salesservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidatorException extends RuntimeException{
    private static final Long serialVerionUID = 1L;
    private final String msg ;

    public ValidatorException(String msg) {
        this.msg = msg;
    }
}
