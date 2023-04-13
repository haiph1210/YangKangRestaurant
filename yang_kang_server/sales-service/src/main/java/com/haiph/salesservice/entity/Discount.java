package com.haiph.salesservice.entity;

import com.haiph.salesservice.Enum.PercentDiscount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "discount")
@Getter
@Setter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String discountCode;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("ZERO")
    private PercentDiscount percentDiscount;
    private LocalDate startDate;
    private LocalDate endDate;
    @PrePersist
    public void perPersis() {
        if (this.percentDiscount == null) {
            this.percentDiscount = PercentDiscount.ZERO;
        }
    }
}
