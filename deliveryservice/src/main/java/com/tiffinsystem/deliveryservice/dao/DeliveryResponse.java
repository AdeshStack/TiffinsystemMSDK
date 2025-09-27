package com.tiffinsystem.deliveryservice.dao;

public record DeliveryResponse(Long deliveryId, Long orderId, Long userId,
                               String address, DeliveryStatus status) {}