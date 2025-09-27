package com.tiffinsystem.deliveryservice.service;


import com.tiffinsystem.deliveryservice.dao.DeliveryRequest;
import com.tiffinsystem.deliveryservice.dao.DeliveryResponse;
import com.tiffinsystem.deliveryservice.dao.DeliveryStatus;
import com.tiffinsystem.deliveryservice.entity.Delivery;
import com.tiffinsystem.deliveryservice.externalservicO.OrderStatus;
import com.tiffinsystem.deliveryservice.externalservicO.OrderStatusUpdateRequest;
import com.tiffinsystem.deliveryservice.repo.DeliveryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeliveryRepo deliveryRepo;

    @Value("${order.service.url}")
    private String orderUrl;


    @Override
    public DeliveryResponse createDelivery(DeliveryRequest request) {

        Delivery delivery= Delivery.builder()
                .orderId(request.orderId())
                .userId(request.userId())
                .address(request.address())
                .status(DeliveryStatus.PENDING)
                .build();

       return mapToResponse(deliveryRepo.save(delivery));
    }

    @Override
    public DeliveryResponse updateStatusDelivery(Long deliveryId, DeliveryStatus status) {

        Delivery delivery = deliveryRepo.findById(deliveryId).orElseThrow(()->new RuntimeException("Delivery not found"));

        delivery.setStatus(status);

      DeliveryResponse response= mapToResponse(deliveryRepo.save(delivery));

      if(DeliveryStatus.DELIVERED.equals(response.status())){
          OrderStatusUpdateRequest orderStatusUpdateRequest=new OrderStatusUpdateRequest(response.orderId(), OrderStatus.DELIVERED);
          restTemplate.postForEntity(orderUrl,orderStatusUpdateRequest,Void.class);
      }
        return response;
    }

    private DeliveryResponse mapToResponse(Delivery delivery) {
        return new DeliveryResponse(
                delivery.getId(),
                delivery.getOrderId(),
                delivery.getUserId(),
                delivery.getAddress(),
                delivery.getStatus()
        );
    }
}