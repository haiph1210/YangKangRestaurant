package com.haiph.menuservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateRequest {
    private Integer id;
    private String menuName;
    private Integer amount;
    private Double price;
    private String imgUrl;
    private LocalDate createdAt;
    private String description;
    private Integer comboId;
//    private Integer orderId;
}
