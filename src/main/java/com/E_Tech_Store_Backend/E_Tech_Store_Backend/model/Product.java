package com.E_Tech_Store_Backend.E_Tech_Store_Backend.model;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.Categorie;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.ETAT;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name , description ;
    private double price;
    private Categorie categorie;
    private ETAT etat;
    private LocalDateTime publication_date;
}
