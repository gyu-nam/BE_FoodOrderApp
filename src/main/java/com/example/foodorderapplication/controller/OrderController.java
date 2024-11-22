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

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderResponseDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id})")
    public OrderResponseDTO getOrderById (@PathVariable Long id) {
        return orderService.getOrdersById(id);
    }

    @PostMapping
    public OrderResponseDTO addOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.addOrder(orderRequestDTO);
    }

    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(@PathVariable Long id, @RequestBody OrderResponseDTO responseDTO){
        return orderService.updateOrder(id, responseDTO);
    }

    @DeleteMapping("/{id}")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }
}