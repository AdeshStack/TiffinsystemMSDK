package com.tiffinsystem.deliveryservice.controller;

import com.tiffinsystem.deliveryservice.dao.DeliveryRequest;
import com.tiffinsystem.deliveryservice.dao.DeliveryResponse;
import com.tiffinsystem.deliveryservice.dao.DeliveryStatus;
import com.tiffinsystem.deliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {


    @Autowired
   private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryResponse> createDelivery(@RequestBody DeliveryRequest request) {
        return ResponseEntity.ok(deliveryService.createDelivery(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DeliveryResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam DeliveryStatus status) {
        return ResponseEntity.ok(deliveryService.updateStatusDelivery(id, status));
    }
}
