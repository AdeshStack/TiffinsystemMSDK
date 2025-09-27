package com.tiffinsystem.deliveryservice.externalservicO;

public record OrderStatusUpdateRequest(Long orderId, OrderStatus status) {
}
