package com.tiffinsystem.order2.ExternalService.FC.PaymentDao;

public record PaymentResponse(Long paymentId, Long orderId, Long userId, Double amount, PaymentStatus status) {}