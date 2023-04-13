package com.haiph.menuservice.model.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class MenuCreateRequest {
    private String name;
    private Double price;
    private Integer amount;
    private String imgUrl;
    private LocalDate createdAt;
    private String description;
    private Integer comboId;
//    private Integer orderId;
}
