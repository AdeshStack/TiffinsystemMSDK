package com.tiffinsystem.productservice.service;

import com.tiffinsystem.productservice.Dao.ProductResponse;
import com.tiffinsystem.productservice.Dao.ProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();


}