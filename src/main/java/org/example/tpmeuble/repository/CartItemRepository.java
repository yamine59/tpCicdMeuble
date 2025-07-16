package org.example.tpmeuble.repository;

import org.example.tpmeuble.entity.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CardItem, UUID> {

}
