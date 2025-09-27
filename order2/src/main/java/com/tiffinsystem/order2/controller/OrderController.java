package com.tiffinsystem.order2.controller;


import com.tiffinsystem.order2.dao.OrderRequest;
import com.tiffinsystem.order2.dao.OrderResponse;
import com.tiffinsystem.order2.dao.OrderStatus;
import com.tiffinsystem.order2.dao.OrderStatusUpdateRequest;
import com.tiffinsystem.order2.entity.Order;
import com.tiffinsystem.order2.repository.OrderRepo;
import com.tiffinsystem.order2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request){
        return  new ResponseEntity<>(orderService.placeOrder(request), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    ResponseEntity<OrderResponse> getByOrder(@PathVariable("orderId") Long orderId ){

        return new ResponseEntity<>(orderService.getByOrderId(orderId),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<List<OrderResponse>> getByUserId(@PathVariable("userId") Long userId){

        return new ResponseEntity<>(orderService.getByUserId(userId),HttpStatus.OK);
    }
    @GetMapping("/all")
    ResponseEntity<List<OrderResponse>> getAllOrder(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{orderId}")
    ResponseEntity<OrderResponse> updateStatus(@PathVariable("orderId") Long orderId, @RequestBody OrderStatus orderStatus){
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId,orderStatus),HttpStatus.OK);
    }

    @GetMapping("/cancelOrder/{orderId}")
    ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>("Canceled the Order",HttpStatus.OK);
    }


    @PostMapping("/updateStatus")
    public ResponseEntity<Void> updateStatusOrder(@RequestBody OrderStatusUpdateRequest request){

        System.out.println("calling order service from delivery");
        Order order = orderRepo.findById(request.orderId()).orElseThrow(()->new RuntimeException("Order Id not present "));

       order.setStatus(request.status());

       this.orderRepo.save(order);

        return ResponseEntity.ok().build();
    }
}
