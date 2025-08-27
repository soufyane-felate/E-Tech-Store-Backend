package com.E_Tech_Store_Backend.E_Tech_Store_Backend.service;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.ClientDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.User;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ClientDto getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ClientDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public long countUsers() {
        return userRepository.count();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
