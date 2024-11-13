package com.example.foodorderapplication.repository;

import com.example.foodorderapplication.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByMenuName(String menuName);
}
