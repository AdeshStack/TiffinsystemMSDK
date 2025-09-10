package com.tiffinsystem.orderservice.Dao;

public enum OrderStatus {
    CREATED,      // Order placed by user
    CONFIRMED,    // Payment confirmed
    IN_PROGRESS,  // Being prepared
    OUT_FOR_DELIVERY, // Assigned to delivery
    DELIVERED,    // Delivered to user
    CANCELLED     // Cancelled by user or system
}
