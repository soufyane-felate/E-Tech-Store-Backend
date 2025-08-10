package com.E_Tech_Store_Backend.E_Tech_Store_Backend.model;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private Long id;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem>items=new ArrayList<>();
}
