package com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}