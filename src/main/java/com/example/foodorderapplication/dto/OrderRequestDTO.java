package com.example.foodorderapplication.dto;

public record OrderRequestDTO(
        Long foodId,
        Long userId,
        Integer count
){}