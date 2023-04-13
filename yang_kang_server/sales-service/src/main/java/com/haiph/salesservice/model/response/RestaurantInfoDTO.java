package com.haiph.salesservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfoDTO {
    private String restaurantName;
    private String hostline;
    private String phoneNumber;
    private String address;
    private String star;
}
