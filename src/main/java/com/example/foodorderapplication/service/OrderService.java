package com.example.foodorderapplication.service;

import com.example.foodorderapplication.dto.FoodDTO;
import com.example.foodorderapplication.dto.OrderRequestDTO;
import com.example.foodorderapplication.dto.OrderResponseDTO;
import com.example.foodorderapplication.dto.UserDTO;
import com.example.foodorderapplication.model.Food;
import com.example.foodorderapplication.model.Order;
import com.example.foodorderapplication.model.User;
import com.example.foodorderapplication.repository.FoodRepository;
import com.example.foodorderapplication.repository.OrderRepository;
import com.example.foodorderapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    // 주문 조회
    @Transactional
    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserUserId(userId);
        List<OrderResponseDTO> orderDTOs = new ArrayList<>();

        for(Order order : orders) {
            orderDTOs.add(convertToDTO(order));
        }
        return orderDTOs;
    }

    // 주문 추가
    @Transactional
    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDto) {
        Order order = convertToEntity(orderRequestDto);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    // 주문 변경
    @Transactional
    public OrderResponseDTO updateOrder(Long orderId, Long userId, OrderResponseDTO dto) {
        Order existingOrder = orderRepository.findByOrderIdAndUserUserId(orderId,userId)
                .orElseThrow(() -> new RuntimeException("Order not found or you don't have access"));
        existingOrder.setFood(foodRepository.findById(dto.food().foodId()).orElseThrow(() -> new RuntimeException("Food not found")));
        existingOrder.setCount(dto.count());
        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDTO(updatedOrder);
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId, Long userId) {
        Order existingOrder = orderRepository.findByOrderIdAndUserUserId(orderId,userId)
                .orElseThrow(() -> new RuntimeException("Order not found or you don't have access"));
        orderRepository.delete(existingOrder);
    }

    // Order Entity >> OrderDTO
    private OrderResponseDTO convertToDTO(Order order) {
        FoodDTO foodDTO = new FoodDTO(order.getFood().getFoodId(), order.getFood().getFoodName(), order.getFood().getPrice());
        UserDTO userDTO = new UserDTO(order.getUser().getUserId(), order.getUser().getUserName(), order.getUser().getEmail());
        return new OrderResponseDTO(order.getId(), foodDTO, userDTO, order.getCount());
    }

    //OrderDTO >> Order Entity
    private Order convertToEntity(OrderRequestDTO dto) {
        Food food = foodRepository.findById(dto.foodId()).orElseThrow(() -> new RuntimeException("Food not found"));
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        return new Order(food, user,dto.count());
    }
}
