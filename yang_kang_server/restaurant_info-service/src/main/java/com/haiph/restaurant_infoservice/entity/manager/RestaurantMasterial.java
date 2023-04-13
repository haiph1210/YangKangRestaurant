package com.haiph.restaurant_infoservice.entity.manager;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurant_masterial")
public class RestaurantMasterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    @CreationTimestamp
    private LocalDate createdAt;
    @OneToMany(mappedBy = "masterial",cascade = {CascadeType.ALL})
    private List<RestaurantDetail> details;

    public List<RestaurantDetail> getDetails() {
        return details;
    }

    public void setDetails(List<RestaurantDetail> details) {
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdDate) {
        this.createdAt = createdDate;
    }


}
