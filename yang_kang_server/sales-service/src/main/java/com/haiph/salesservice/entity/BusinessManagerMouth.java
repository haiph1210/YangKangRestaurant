package com.haiph.salesservice.entity;

import com.haiph.salesservice.Enum.Mouth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessManagerMouth {
    @Id
    @Enumerated(EnumType.STRING)
    private Mouth mouth;
    private Integer year;
    private Double totalMoney;
    private Integer totalPayment;
}
