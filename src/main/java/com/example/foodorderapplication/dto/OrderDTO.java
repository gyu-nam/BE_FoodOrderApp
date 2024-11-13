package com.example.foodorderapplication.dto;

public record OrderDTO(
        Long id,
        String menuName,
        Integer count
){}