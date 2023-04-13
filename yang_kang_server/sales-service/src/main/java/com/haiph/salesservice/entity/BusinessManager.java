package com.haiph.salesservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "business_manager")
@Getter
@Setter
public class BusinessManager {
    @Id
    private LocalDate day;
    private Integer totalPayment;
//    private Integer totalDayOfMounth; // để bảng tháng
    private Integer mounth;
    private Integer year;
    private Double totalMoney;

    @PrePersist
    private void prePersist() {
        if (this.totalMoney == null) {
            this.totalMoney = 0d;
        }
    }
}
