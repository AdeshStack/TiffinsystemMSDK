package com.tiffinsystem.orderservice.repository;


import com.tiffinsystem.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    // adding custom method to fetch from user and product

    List<Order> findByUserId(Long userId);

    List<Order> findByProductId(Long productId);
}
