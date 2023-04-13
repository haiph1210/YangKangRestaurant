package com.haiph.orderservice.model.request;

import com.haiph.orderservice.Enum.Form;
import com.haiph.orderservice.Enum.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderUpdate {
    private Integer id;
    private Form form;
    private LocalDateTime hour;
    private Status status;
    private List<Integer> menus;
    private List<Integer> combos;

}
