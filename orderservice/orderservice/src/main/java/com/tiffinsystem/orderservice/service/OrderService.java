package com.tiffinsystem.orderservice.service;



import com.tiffinsystem.orderservice.Dao.OrderRequest;
import com.tiffinsystem.orderservice.Dao.OrderResponse;
import com.tiffinsystem.orderservice.Dao.OrderStatus;


import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

    OrderResponse getByOrderId(Long orderId);

    List<OrderResponse> getByUserId(Long userId);

    List<OrderResponse> getAllOrders();

    OrderResponse updateOrderStatus(Long orderId, OrderStatus status);

    void cancelOrder(Long orderId);

}
