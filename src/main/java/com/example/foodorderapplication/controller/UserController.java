package com.example.foodorderapplication.controller;

import com.example.foodorderapplication.dto.SignUpRequestDTO;
import com.example.foodorderapplication.dto.UserDTO;
import com.example.foodorderapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        UserDTO createdUser = userService.createUser(signUpRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
