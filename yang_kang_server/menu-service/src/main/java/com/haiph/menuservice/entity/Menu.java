package com.haiph.menuservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer Id;
    @Column
    private String name;
    @Column
    private Double price;
    private Double initPrice;
    @Column
    private String imgUrl;

    @Column
    @CreationTimestamp
    private LocalDate createdAt;
    @Column
    private String description;
    @Column
    @ColumnDefault(value = "1")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "combo_id" ,referencedColumnName = "id")
    private Combo combo;

    public Double getInitPrice() {
        return this.price*this.amount ;
    }
    public void setInitPrice(Double initPrice) {
        this.initPrice = this.price * this.amount;
    }


}
