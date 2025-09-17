package com.tiffinsystem.order2.service;


import com.tiffinsystem.order2.dao.OrderRequest;
import com.tiffinsystem.order2.dao.OrderResponse;
import com.tiffinsystem.order2.dao.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

    OrderResponse getByOrderId(Long orderId);

    List<OrderResponse> getByUserId(Long userId);

    List<OrderResponse> getAllOrders();

    OrderResponse updateOrderStatus(Long orderId, OrderStatus status);

    void cancelOrder(Long orderId);

}
