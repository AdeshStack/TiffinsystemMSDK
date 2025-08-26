package com.tiffinsystem.productservice.service;


import com.tiffinsystem.productservice.Dao.ProductRequest;
import com.tiffinsystem.productservice.Dao.ProductResponse;
import com.tiffinsystem.productservice.entity.Product;
import com.tiffinsystem.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product=Product
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .brand(request.getBrand())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .status(request.getStatus())
                .build();

        Product save=this.productRepo.save(product);

        ProductResponse response=ProductResponse.builder()
                .name(save.getName())
                .description(save.getDescription())
                .brand(save.getBrand())
                .price(save.getPrice())
                .quantity(save.getQuantity())
                .category(save.getCategory())
                .status(save.getStatus())
                .createdAt(save.getCreatedAt())
                .updatedAt(save.getUpdatedAt())
                .build();

        return response;
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProductResponse getById(Long id) {
        return null;
    }

    @Override
    public List<ProductResponse> getAll() {
        return List.of();
    }
}
