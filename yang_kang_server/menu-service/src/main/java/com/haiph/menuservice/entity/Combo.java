package com.haiph.menuservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Combo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private NumberOfPeople numberOfPeople;
    private Double initPrice;
    private Double price;
    private String imgUrl;
    private String description;
    @OneToMany(mappedBy = "combo",cascade = {CascadeType.ALL})
    private List<Menu> menus;

    public enum NumberOfPeople{
        _4,_8,_12,_20
    }

    public Double getInitPrice() {
        return this.price *this.amount;
    }

    public void setInitPrice(Double initPrice) {
        this.initPrice = this.price *this.amount;
    }
}
