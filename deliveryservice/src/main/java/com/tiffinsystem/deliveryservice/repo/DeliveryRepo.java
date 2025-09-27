package com.tiffinsystem.deliveryservice.repo;

import com.tiffinsystem.deliveryservice.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepo extends JpaRepository<Delivery,Long> {

    List<Delivery> findByOrderId(Long orderId);

}
