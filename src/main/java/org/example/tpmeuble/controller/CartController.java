package org.example.tpmeuble.controller;

import org.example.tpmeuble.entity.CardItem;
import org.example.tpmeuble.entity.Furniture;
import org.example.tpmeuble.service.CartService;
import org.example.tpmeuble.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final FurnitureService furnitureService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
        this.furnitureService = furnitureService;

    }


    @GetMapping("/list")
    public String homePage(Model model){
        model.addAttribute("furnitures",cartService.getAllCartItems());
        return "cart-list";
    }

    @PostMapping("/add")
    public String saveOrUpdate(@RequestParam("furniture") UUID furnitureId,
                               @RequestParam("quantity") int quantity) {

        Furniture furniture = furnitureService.getFurnitureById(furnitureId);

        CardItem cardItem = new CardItem();
        cardItem.setFurniture(furniture);
        cardItem.setQuantity(quantity);

        cartService.addToCard(cardItem);

        return "redirect:/cart/list";
    }



    @GetMapping("/remove-to-card")
    public String delete(@ModelAttribute CardItem cardItem){
        cartService.removeToCard(cardItem);
        return "redirect:/list";
    }

    @GetMapping("/clear")
    public String clearCart(){
        cartService.clearCart();
        return "redirect:/list";
    }
}
