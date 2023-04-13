package com.haiph.restaurant_infoservice.model.response.manager;

import com.haiph.restaurant_infoservice.entity.manager.RestaurantDetail;
import lombok.*;

@Getter
@Setter
public class RestaurantDetailDTO {
    private String name;
    private Double price;
    private String description;
    private Integer amount;
    private Double initPrice;
    private String masterialName;
}
