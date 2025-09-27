package com.tiffinsystem.deliveryservice.dao;

public record DeliveryRequest(Long orderId, Long userId, String address) {}
