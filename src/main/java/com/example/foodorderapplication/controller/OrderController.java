package com.example.foodorderapplication.controller;

import com.example.foodorderapplication.dto.OrderDTO;
import com.example.foodorderapplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String displayMenu() {
        return "menu";
    }

    @GetMapping("/orders")
    public String displayOrders(Model model) {
        List<OrderDTO> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orderList";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute OrderDTO dto) {
        orderService.addOrder(dto);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateOrderForm(@PathVariable Long id, Model model) {
        OrderDTO dto = orderService.getOrdersById(id);
        model.addAttribute("order", dto);
        return "editOrderForm";
    }

    @PostMapping("/edit")
    public String updateOrder(@ModelAttribute OrderDTO dto) {
        orderService.updateOrder(dto.id(), dto);
        return "redirect:/orders";
    }

    @PostMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }

}