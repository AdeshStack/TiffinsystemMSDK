package com.tiffinsystem.payment.Dao;

public record PaymentRequest(Long orderId, Long userId, Double amount) {}