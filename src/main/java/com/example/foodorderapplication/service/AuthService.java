package com.example.foodorderapplication.service;

import com.example.foodorderapplication.dto.LoginRequestDTO;
import com.example.foodorderapplication.dto.SignUpRequestDTO;
import com.example.foodorderapplication.dto.UserDTO;
import com.example.foodorderapplication.model.User;
import com.example.foodorderapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 기능
    public UserDTO signUp(SignUpRequestDTO signUpRequest) {

        if(userRepository.existsByEmail(signUpRequest.email())){
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.password());

        User user = new User(signUpRequest.userName(), signUpRequest.email(), encodedPassword);
        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser.getUserId(), savedUser.getUserName(),savedUser.getEmail());
    }

    // 로그인 기능
    public UserDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found such email"));

        if(!passwordEncoder.matches(loginRequest.password(), user.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }

        return new UserDTO(user.getUserId(),user.getUserName(), user.getEmail());
    }
}
