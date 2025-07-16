package org.example.tpmeuble.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCard;
    @OneToOne
    @JoinColumn(name = "idFurniture")
    private Furniture furniture;
    private int quantity;

}
