package com.haiph.salesservice.model.response;

import com.haiph.salesservice.Enum.PaymentBy;
import com.haiph.salesservice.Enum.PercentDiscount;
import com.haiph.salesservice.Enum.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {
    private Integer id;
    private RestaurantInfoDTO info;
    private PersonDTO person;
    private Double customerPay;
    private Double initPrice;
    private Double moneyRemain;
    private PaymentBy paymentBy;
    private LocalDateTime createdAt;
    private String discountCode;
    private Status status;
    private OrderDTO order;

}
