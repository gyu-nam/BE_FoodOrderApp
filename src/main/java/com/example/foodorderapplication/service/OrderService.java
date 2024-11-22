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

    // Order Entity >> OrderDTO
    public OrderResponseDTO convertToDTO(Order order) {
        FoodDTO foodDTO = new FoodDTO(order.getFood().getFoodId(), order.getFood().getFoodName(), order.getFood().getPrice());
        UserDTO userDTO = new UserDTO(order.getUser().getUserId(), order.getUser().getUserName());
        return new OrderResponseDTO(order.getId(), foodDTO, userDTO, order.getCount());
    }

    //OrderDTO >> Order Entity
    public Order convertToEntity(OrderRequestDTO dto) {
        Food food = foodRepository.findById(dto.foodId()).orElseThrow(() -> new RuntimeException("Food not found"));
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        return new Order(null, food, user,dto.count());
    }

    // 주문 조회
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        List<OrderResponseDTO> orderDTOs = new ArrayList<>();

        for(Order order : orders) {
            orderDTOs.add(convertToDTO(order));
        }
        return orderDTOs;
    }

    public OrderResponseDTO getOrdersById(Long id) {
       Order order = orderRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    // 주문 추가
    @Transactional
    public OrderResponseDTO addOrder(OrderRequestDTO dto) {
        Order order = convertToEntity(dto);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    // 주문 변경
    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderResponseDTO dto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setFood(foodRepository.findById(dto.food().id()).orElseThrow(() -> new RuntimeException("Food not found")));
        existingOrder.setUser(userRepository.findById(dto.user().id()).orElseThrow(() -> new RuntimeException("User not found")));
        existingOrder.setCount(dto.count());
        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDTO(updatedOrder);
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(existingOrder);
    }
}
