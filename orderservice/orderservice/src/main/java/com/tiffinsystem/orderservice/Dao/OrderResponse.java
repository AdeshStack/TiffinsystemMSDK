package com.tiffinsystem.orderservice.Dao;

import java.time.LocalDateTime;

public record OrderResponse(Long id,
                           Long userId,
                           Long productId,
                           Integer quantity,
                           Double price,
                           OrderStatus status,
                           LocalDateTime createdAt) {
}
