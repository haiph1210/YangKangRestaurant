package com.haiph.salesservice.Enum;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS("Thành Công"),
    FAIL("Thất Bại"),
    WAIT_FOR_PAY("Chờ Thanh Toán")

    ;

    Status(String message) {
        this.message = message;
    }

    private final String message;
}
