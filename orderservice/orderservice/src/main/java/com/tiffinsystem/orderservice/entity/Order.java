package com.tiffinsystem.orderservice.entity;

import com.tiffinsystem.orderservice.Dao.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "Orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Long product_id;

    private  Integer quantity;

    private Double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    @PrePersist  // This method will be called automatically before saving a new entity
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
