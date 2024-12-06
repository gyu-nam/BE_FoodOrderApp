package com.example.foodorderapplication.controller;

import com.example.foodorderapplication.dto.LoginRequestDTO;
import com.example.foodorderapplication.dto.SignUpRequestDTO;
import com.example.foodorderapplication.dto.UserDTO;
import com.example.foodorderapplication.model.User;
import com.example.foodorderapplication.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDTO signUpRequest) {
        authService.signUp(signUpRequest);
        return ResponseEntity.ok("Sign up successful");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequest, HttpSession session) {
        UserDTO userDTO = authService.login(loginRequest);

        session.setAttribute("userId", userDTO.userId());
        session.setAttribute("userName", userDTO.userName());

        return ResponseEntity.ok(userDTO);

    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("logout successfully");
    }



}
