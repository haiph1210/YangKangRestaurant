package com.haiph.orderservice.model.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Combo {
    private String name;
    private Double price;
    private Integer amount;
    private Double initPrice;
    private String numberOfPeople;
    private List<Menu> menus;
}

