package com.example.foodorderapplication.dto;

public record OrderResponseDTO(
        Long id,
        FoodDTO food,
        UserDTO user,
        Integer count
){}