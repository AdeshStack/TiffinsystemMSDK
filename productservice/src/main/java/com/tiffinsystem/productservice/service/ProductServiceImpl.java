package com.tiffinsystem.productservice.service;


import com.tiffinsystem.productservice.Dao.ProductRequest;
import com.tiffinsystem.productservice.Dao.ProductResponse;
import com.tiffinsystem.productservice.entity.Product;
import com.tiffinsystem.productservice.exception.ResourceNotFoundException;
import com.tiffinsystem.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
                .id(save.getId())
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

        Product product=this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found for this id: "+id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setStatus(request.getStatus());

       Product save= this.productRepo.save(product);

        ProductResponse response=ProductResponse.builder()
                .id(save.getId())
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
    public void delete(Long id) {

     Product product=this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with this id: "+id));

    this.productRepo.delete(product);

    }

    @Override
    public ProductResponse getById(Long id) {

        Product save=this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with this id: "+id));

        ProductResponse response=ProductResponse.builder()
                .id(save.getId())
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
    public List<ProductResponse> getAll() {

        List<Product> list=this.productRepo.findAll();
        List<ProductResponse> responseList= list.stream().map(
                save -> ProductResponse.builder()
                        .id(save.getId())
                        .name(save.getName())
                        .description(save.getDescription())
                        .brand(save.getBrand())
                        .price(save.getPrice())
                        .quantity(save.getQuantity())
                        .category(save.getCategory())
                        .status(save.getStatus())
                        .createdAt(save.getCreatedAt())
                        .updatedAt(save.getUpdatedAt())
                        .build()
        ).collect(Collectors.toList());
        return  responseList;
    }
}
