package com.haiph.salesservice.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private String form;
//    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime hour;
    private String status;
    private Integer totalAmount;
    private Double initPrice;
    private List<Menu> menus;
    private List<Combo> combos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Menu{
        private String name;
        private Double price;
        private Integer amount;
        private Double initPrice;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Combo{
        private String name;
        private Double price;
        private Integer amount;
        private Double initPrice;
        private String numberOfPeople;
        private List<Menu> menus;
        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Menu{
            private String name;
        }
    }
}