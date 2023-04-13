package com.haiph.restaurant_infoservice.model.response.manager;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMasterialDTO {
    private Integer id;
    private String name;
    private List<RestaurantDetailDTO> details;
    @Getter
    @Setter
    public static class RestaurantDetailDTO {
        private Integer id;
        private String name;
        private Double price;
        private String description;
        private Integer amount;
        private Double initPrice;
    }
}
