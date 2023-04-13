package com.haiph.menuservice.model.response;

import com.haiph.menuservice.entity.Combo;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer amount;
    private Double initPrice;
    private Combo.NumberOfPeople numberOfPeople;
    private String imgUrl;
    private String description;
    private List<MenuDTO> menus;
    @Getter
    @Setter
    public static class MenuDTO {
        private Integer id;
        private String name;
        private Integer amount;
        private Float price;
        private String imgUrl;
    }
}
