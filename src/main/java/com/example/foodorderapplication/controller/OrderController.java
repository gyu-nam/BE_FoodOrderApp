package com.example.foodorderapplication.controller;

import com.example.foodorderapplication.dto.OrderRequestDTO;
import com.example.foodorderapplication.dto.OrderResponseDTO;
import com.example.foodorderapplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 조회
    @GetMapping
    public List<OrderResponseDTO> getAllOrders(@SessionAttribute("userId") Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // 주문 추가
    @PostMapping
    public OrderResponseDTO addOrder(@RequestBody OrderRequestDTO orderRequestDTO, @SessionAttribute("userId") Long userId) {
        OrderRequestDTO updatedOrderRequestDTO = new OrderRequestDTO(
                orderRequestDTO.foodId(),
                userId,
                orderRequestDTO.count()
        );

        return orderService.addOrder(updatedOrderRequestDTO);
    }

    // 주문 수정
    @PutMapping("/{orderId}")
    public OrderResponseDTO updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderResponseDTO responseDTO, @SessionAttribute("userId") Long userId) {
        return orderService.updateOrder(orderId, userId, responseDTO);
    }

    // 주문 취소
    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable("orderId") Long orderId, @SessionAttribute("userId") Long userId) {
        orderService.cancelOrder(orderId, userId);
    }
}