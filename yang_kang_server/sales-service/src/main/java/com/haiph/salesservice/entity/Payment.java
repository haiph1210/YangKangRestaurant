package com.haiph.salesservice.entity;

import com.haiph.salesservice.Enum.PaymentBy;
import com.haiph.salesservice.Enum.PercentDiscount;
import com.haiph.salesservice.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String personCode;
    private Integer orderId;
    private Integer restaurantInfoId;
    private Double customerPay;
    private Double moneyRemain;
    private Double initPrice;
    @Enumerated(EnumType.STRING)
    private PaymentBy paymentBy;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String discountCode;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    public Payment() {
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = Status.FAIL;
        }
    }

    private Double calculateRemain (Double initPrice, Double customerPay) {
        return customerPay - initPrice;
    }
}
