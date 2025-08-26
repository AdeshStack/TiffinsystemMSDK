package com.tiffinsystem.productservice.Dao;


import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    private String category;
    private String brand;
    private String status;
}
