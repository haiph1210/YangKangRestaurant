package com.haiph.menuservice.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class MenuDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer amount;
    private Double initPrice;
    private String imgUrl;
    private Integer discountCode;
    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate createdAt;
    private String description;
    private String comboName;
}
