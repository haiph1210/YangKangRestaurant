package com.haiph.orderservice.model.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String name;
    private Double price;
    private Integer amount;
    private Double initPrice;
}