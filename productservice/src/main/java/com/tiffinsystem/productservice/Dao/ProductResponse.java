package com.tiffinsystem.productservice.Dao;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    private String category;
    private String brand;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String status;
}
