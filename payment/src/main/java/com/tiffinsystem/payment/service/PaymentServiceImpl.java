package com.tiffinsystem.payment.service;

import com.tiffinsystem.payment.Dao.PaymentRequest;
import com.tiffinsystem.payment.Dao.PaymentResponse;
import com.tiffinsystem.payment.Dao.PaymentStatus;
import com.tiffinsystem.payment.entity.Payment;
import com.tiffinsystem.payment.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo repo;

    @Override
    public PaymentResponse makePayment(PaymentRequest request) {
        // Mock payment gateway simulation
        Payment payment = Payment.builder()
                .orderId(request.orderId())
                .userId(request.userId())
                .amount(request.amount())
                .status(Math.random() > 0.2 ? PaymentStatus.SUCCESS : PaymentStatus.FAILED) // 80% success chance
                .build();

        Payment saved = repo.save(payment);

        return new PaymentResponse(saved.getId(), saved.getOrderId(),
                saved.getUserId(), saved.getAmount(), saved.getStatus());
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        Payment payment = repo.findByOrderId(orderId).orElseThrow();
        return new PaymentResponse(payment.getId(), payment.getOrderId(),
                payment.getUserId(), payment.getAmount(), payment.getStatus());
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return repo.findAll()
                .stream()
                .map(p -> new PaymentResponse(p.getId(), p.getOrderId(),
                        p.getUserId(), p.getAmount(), p.getStatus()))
                .toList();
    }
}
