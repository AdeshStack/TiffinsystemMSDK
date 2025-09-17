package com.tiffinsystem.order2.repository;

import com.tiffinsystem.order2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long userId);

}
