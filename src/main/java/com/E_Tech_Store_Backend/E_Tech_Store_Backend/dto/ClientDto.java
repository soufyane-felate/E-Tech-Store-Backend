package com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private String name;
    private String email;
    private Role role;
}
