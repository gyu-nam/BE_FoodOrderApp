package com.example.foodorderapplication.service;

import com.example.foodorderapplication.dto.OrderDTO;
import com.example.foodorderapplication.model.Order;
import com.example.foodorderapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Order Entity >> OrderDTO
    public OrderDTO converToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getMenuName(),
                order.getCount()
        );
    }

    //OrderDTO >> Order Entity
    public Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setMenuName(dto.menuName());
        order.setCount(dto.count());
        return order;
    }

    // 주문 조회
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for(Order order : orders) {
            orderDTOs.add(converToDTO(order));
        }
        return orderDTOs;
    }

    public OrderDTO getOrdersById(Long id) {
       Order order = orderRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Order not found"));
        return converToDTO(order);
    }

    // 주문 추가
    public OrderDTO addOrder(OrderDTO dto) {
        Order order = convertToEntity(dto);
        Order savedOrder = orderRepository.save(order);
        return converToDTO(savedOrder);
    }

    // 주문 변경
    public OrderDTO updateOrder(Long id, OrderDTO dto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setMenuName(dto.menuName());
        existingOrder.setCount(dto.count());
        Order updatedOrder = orderRepository.save(existingOrder);
        return converToDTO(updatedOrder);
    }

    // 주문 취소
    public void cancelOrder(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(existingOrder);
    }
}
