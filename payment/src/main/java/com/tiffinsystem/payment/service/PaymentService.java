package com.tiffinsystem.payment.service;


import com.tiffinsystem.payment.Dao.PaymentRequest;
import com.tiffinsystem.payment.Dao.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse makePayment(PaymentRequest request);
    PaymentResponse getPaymentByOrderId(Long orderId);
    List<PaymentResponse> getAllPayments();
}
