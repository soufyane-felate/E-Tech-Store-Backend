package com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.Categorie;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.ETAT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductDto {
    private Long id;
    private String name ,image, description ;
    private double price;
    private Categorie categorie;
    private ETAT etat;
    private LocalDateTime publication_date;
}
