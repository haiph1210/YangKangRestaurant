package com.haiph.restaurant_infoservice.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "restaurant_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String restaurantName;
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
    @Column
    @Enumerated(EnumType.STRING)
    private Star star;
    @Column(name = "start_begin")
    private LocalDate createdAt;
    @Column
    private String imgUrl;

    public enum Star{
        ONE,TWO,THREE,FOUR,FIVE
    }
}
