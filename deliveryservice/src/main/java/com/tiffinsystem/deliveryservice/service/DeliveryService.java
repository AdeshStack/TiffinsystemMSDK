package com.tiffinsystem.deliveryservice.service;

import com.tiffinsystem.deliveryservice.dao.DeliveryRequest;
import com.tiffinsystem.deliveryservice.dao.DeliveryResponse;
import com.tiffinsystem.deliveryservice.dao.DeliveryStatus;

public interface DeliveryService {

    DeliveryResponse createDelivery(DeliveryRequest request);

    DeliveryResponse updateStatusDelivery(Long deliveryId, DeliveryStatus status);

}
