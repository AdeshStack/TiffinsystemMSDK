package com.tiffinsystem.orderservice.Dao;

import java.time.LocalDateTime;

public record OrderResponse(Long id,
                           Long user_id,
                           Long product_id,
                           Integer quantity,
                           Double price,
                           OrderStatus status,
                           LocalDateTime createdAt) {
}
