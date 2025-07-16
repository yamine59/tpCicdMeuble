package org.example.tpmeuble.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idFurniture;
    private String name;
    private String description;
    private double price;
    private int stock;

}
