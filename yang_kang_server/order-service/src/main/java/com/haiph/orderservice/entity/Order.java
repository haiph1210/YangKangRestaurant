package com.haiph.orderservice.entity;

import com.haiph.orderservice.Enum.Form;
import com.haiph.orderservice.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.ORDINAL)
    private Form form;
    private Integer totalAmount;
    private Double initPrice;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDateTime hour;
    @ColumnDefault("CREATED")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    @CollectionTable(name = "order_menu_ids", joinColumns = @JoinColumn(name = "order_id"))
    @JoinColumn(name = "`order_id`", referencedColumnName = "`id`")
    private List<Integer> menuIds;

    @ElementCollection
    @CollectionTable(name = "order_combo_ids", joinColumns = @JoinColumn(name = "order_id"))
    @JoinColumn(name = "`order_id`", referencedColumnName = "`id`")
    private List<Integer> comboIds;

}
