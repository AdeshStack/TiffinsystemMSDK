//package com.tiffinsystem.order2.ExternalService.FC;
//
//
//import com.tiffinsystem.order2.ExternalService.FC.PaymentDao.PaymentRequest;
//import com.tiffinsystem.order2.ExternalService.FC.PaymentDao.PaymentResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "paymentservice-service")
//public interface PaymentService {
//    @PostMapping("/api/payments/click")
//    PaymentResponse makePayment(@RequestBody PaymentRequest request);
//}
