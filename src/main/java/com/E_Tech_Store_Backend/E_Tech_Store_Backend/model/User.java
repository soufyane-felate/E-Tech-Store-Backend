package com.E_Tech_Store_Backend.E_Tech_Store_Backend.model;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name,password;
    @Column(unique = true,nullable = false)
    private String email;
    private LocalDate registration_date;
    private Role role;
}
