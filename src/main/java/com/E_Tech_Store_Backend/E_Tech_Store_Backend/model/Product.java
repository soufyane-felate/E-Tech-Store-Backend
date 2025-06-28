package com.E_Tech_Store_Backend.E_Tech_Store_Backend.model;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.Categorie;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.ETAT;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private String description;
    private double price;
    private Categorie categorie;
    private ETAT etat;

    @CreationTimestamp
    @Column(name = "publication_date", nullable = false, updatable = false)
    private LocalDateTime publicationDate;
}
