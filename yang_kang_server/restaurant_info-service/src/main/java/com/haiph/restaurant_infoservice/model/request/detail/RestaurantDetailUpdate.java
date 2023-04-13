package com.haiph.restaurant_infoservice.model.request.detail;

import lombok.*;

@Getter
@Setter
public class RestaurantDetailUpdate {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Integer amount;
    private Double initPrice;
    private Integer masterialId;
}
