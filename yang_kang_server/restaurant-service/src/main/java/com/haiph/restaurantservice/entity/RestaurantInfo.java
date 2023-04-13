package com.haiph.restaurantservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Byte id;
    @Column
    private String name;
    @Column
    private String hostline;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String description;
}
