package com.haiph.salesservice.model.request;

import com.haiph.salesservice.Enum.PaymentBy;
import com.haiph.salesservice.Enum.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PaymentCreate {
    private String personCode;
    private Integer orderId;
    private Integer restaurantInfoId;
    private Double customerPay;
    private Double moneyRemain;
    private PaymentBy paymentBy;
    private String discountCode;
    private LocalDateTime createdAt;
    private Status status;
}
