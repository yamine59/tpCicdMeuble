package org.example.tpmeuble.repository;

import org.example.tpmeuble.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FurnitureRepository extends JpaRepository<Furniture, UUID> {
}
