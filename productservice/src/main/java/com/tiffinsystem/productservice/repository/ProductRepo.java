package com.tiffinsystem.productservice.repository;

import com.tiffinsystem.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
