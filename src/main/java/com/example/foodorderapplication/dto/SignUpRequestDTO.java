package com.example.foodorderapplication.dto;

public record SignUpRequestDTO(
        String userName,
        String email,
        String password
) {}
