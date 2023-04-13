package com.haiph.orderservice.model.response.order;

import com.haiph.orderservice.Enum.Form;
import com.haiph.orderservice.Enum.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Status status;
    private Form form;
    private Double initPrice;
    private Integer totalAmount;
    private LocalDate createdAt;
    private LocalDateTime hour;
    private List<Menu> menus;
    private List<Combo> combos;
}
