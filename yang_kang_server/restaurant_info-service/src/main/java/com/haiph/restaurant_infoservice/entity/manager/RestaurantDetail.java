package com.haiph.restaurant_infoservice.entity.manager;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant_detail")
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private String description;
    @Column
    private Integer amount;
    @Column
    private Double initPrice;
   @ManyToOne
   @JoinColumn(name = "masterial_id",referencedColumnName = "id")
    private RestaurantMasterial masterial;

    public RestaurantDetail(String name, Double price, String description, Integer amount, Double initPrice) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.amount = amount;
        this.initPrice = initPrice;
    }

    public RestaurantMasterial getMasterial() {
        return masterial;
    }

    public void setMasterial(RestaurantMasterial masterial) {
        this.masterial = masterial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getInitPrice() {
        return this.amount*this.price;
    }

    public void setInitPrice(Double initPrice) {
        this.initPrice = initPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
