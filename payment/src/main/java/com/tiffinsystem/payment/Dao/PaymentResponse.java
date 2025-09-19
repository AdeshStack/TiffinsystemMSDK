package com.tiffinsystem.payment.Dao;

public record PaymentResponse(Long paymentId, Long orderId, Long userId, Double amount, PaymentStatus status) {}