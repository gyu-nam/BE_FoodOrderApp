package com.example.foodorderapplication.dto;

public record OrderResponseDTO(
        Long orderId,
        FoodDTO food,
        UserDTO user,
        Integer count
){}