package com.tiffinsystem.productservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;
    private Integer quantity;

    private String category;
    private String brand;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String status; // AVAILABLE, OUT_OF_STOCK, DISCONTINUED

    @PrePersist  // This method will be called automatically before saving a new entity
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = "AVAILABLE";
    }

    @PreUpdate  // automatically update when you update exiting entity
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



}
