package com.example.foodorderapplication.repository;

import com.example.foodorderapplication.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserUserId(Long userId);
    Optional<Order> findByOrderIdAndUserUserId(Long orderId, Long userId);
}
