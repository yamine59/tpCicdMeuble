package org.example.tpmeuble.service;

import org.example.tpmeuble.entity.CardItem;
import org.example.tpmeuble.entity.Furniture;
import org.example.tpmeuble.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class CartService {
    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CardItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void addToCard(CardItem cardItem){
        Furniture furniture = cardItem.getFurniture();
        int c = cardItem.getQuantity();
        furniture.setStock(furniture.getStock()-c);
        cartItemRepository.save(cardItem);
    }

    public void removeToCard(CardItem cardItem){
        Furniture furniture = cardItem.getFurniture();
        int c = cardItem.getQuantity();
        furniture.setStock(furniture.getStock()+c);
        cartItemRepository.save(cardItem);
    }

    public void clearCart(){
        for (CardItem cart : cartItemRepository.findAll()){
            removeToCard(cart);
        }
    }
}
