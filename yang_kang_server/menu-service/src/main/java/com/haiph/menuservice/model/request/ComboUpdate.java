package com.haiph.menuservice.model.request;

import com.haiph.menuservice.entity.Combo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboUpdate {
    private Integer id;
    private String name;
    private Integer amount;
    private Combo.NumberOfPeople numberOfPeople;
    private Double price;
    private String imgUrl;
    private String description;
//    private Integer orderId;
}
