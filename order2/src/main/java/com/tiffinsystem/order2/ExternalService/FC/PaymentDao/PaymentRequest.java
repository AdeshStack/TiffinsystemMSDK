package com.tiffinsystem.order2.ExternalService.FC.PaymentDao;

public record PaymentRequest(Long orderId, Long userId, Double amount) {}