package com.example.foodorderapplication.service;

import com.example.foodorderapplication.dto.UserDTO;
import com.example.foodorderapplication.model.User;
import com.example.foodorderapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 생성
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(null, userDTO.userName());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUserId(), savedUser.getUserName());
    }


}
