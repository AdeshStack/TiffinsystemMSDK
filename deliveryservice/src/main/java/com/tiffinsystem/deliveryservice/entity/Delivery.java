package com.tiffinsystem.deliveryservice.entity;

import com.tiffinsystem.deliveryservice.dao.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;       // Reference to order
    private Long userId;        // Who placed the order
    private String address;     // Delivery address

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime createdAt;

    @PrePersist  // This method will be called automatically before saving a new entity
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
