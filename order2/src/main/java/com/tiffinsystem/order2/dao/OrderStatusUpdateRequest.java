package com.tiffinsystem.order2.dao;

public record OrderStatusUpdateRequest(Long orderId, OrderStatus status) {
}
