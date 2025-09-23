package com.tiffinsystem.order2.service;



import com.tiffinsystem.order2.ExternalService.FC.PaymentDao.PaymentRequest;
import com.tiffinsystem.order2.ExternalService.FC.PaymentDao.PaymentResponse;
import com.tiffinsystem.order2.dao.OrderRequest;
import com.tiffinsystem.order2.dao.OrderResponse;
import com.tiffinsystem.order2.dao.OrderStatus;
import com.tiffinsystem.order2.entity.Order;
import com.tiffinsystem.order2.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepo repo;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = Order.builder()
                .userId(request.userId())
                .productId(request.productId())
                .quantity(request.quantity())
                .price(request.price())
                .status(OrderStatus.CREATED)
                .build();

       Order save= this.repo.save(order);

        // Call payment-service in Kubernetes
      String paymentUrl = "http://paymentservice-service/api/payments/click";

      PaymentRequest payment = new PaymentRequest(order.getId(), order.getUserId(), order.getPrice());

        ResponseEntity<PaymentResponse> response =
                restTemplate.postForEntity(paymentUrl, payment, PaymentResponse.class);

        if (response.getBody() != null && "SUCCESS".equals(response.getBody().status())) {
            System.out.println("PAYMENT SERVICE CALLED BY ORDER SERVICE");
            order.setStatus(OrderStatus.CONFIRMED);
        } else {
            order.setStatus(OrderStatus.CANCELLED);
        }
        
        return mapToResponse(repo.save(order));
    }

    @Override
    public OrderResponse getByOrderId(Long orderId) {

        Order order =this.repo.findById(orderId).orElseThrow();

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getByUserId(Long userId) {

        List<Order> orders=this.repo.findByUserId(userId);

        List<OrderResponse> response= orders.stream().map(res->mapToResponse(res)).toList();

        return  response;

    }

    @Override
    public List<OrderResponse> getAllOrders() {

        List<Order> orders=this.repo.findAll();
        List<OrderResponse> response= orders.stream().map(
                res-> new OrderResponse(
                res.getId()
                ,res.getUserId()
                ,res.getProductId(),
                res.getQuantity(),
                res.getPrice(),
                res.getStatus(),
                res.getCreatedAt())

        ).collect(Collectors.toList());


        return  response;

    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus status) {

        Order order=this.repo.findById(orderId).orElseThrow();
        order.setStatus(status);
        Order save=this.repo.save(order);
        return mapToResponse(save);
    }

    @Override
    public void cancelOrder(Long orderId) {

            Order order = this.repo.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
            order.setStatus(OrderStatus.CANCELLED);
            this.repo.save(order);
        }


    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

}
