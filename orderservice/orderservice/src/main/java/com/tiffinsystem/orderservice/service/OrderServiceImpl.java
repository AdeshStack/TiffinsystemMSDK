package com.tiffinsystem.orderservice.service;



import com.tiffinsystem.orderservice.Dao.OrderRequest;
import com.tiffinsystem.orderservice.Dao.OrderResponse;
import com.tiffinsystem.orderservice.Dao.OrderStatus;
import com.tiffinsystem.orderservice.entity.Order;
import com.tiffinsystem.orderservice.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo repo;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = Order.builder()
                .user_id(request.user_id())
                .product_id(request.product_id())
                .quantity(request.quantity())
                .price(request.price())
                .status(OrderStatus.CREATED)
                .build();

       Order save= this.repo.save(order);

        return mapToResponse(save);
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
                ,res.getUser_id()
                ,res.getProduct_id(),
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
                order.getUser_id(),
                order.getProduct_id(),
                order.getQuantity(),
                order.getPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

}
