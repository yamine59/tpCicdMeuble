package org.example.tpmeuble.controller;

import org.example.tpmeuble.entity.CardItem;
import org.example.tpmeuble.entity.Furniture;
import org.example.tpmeuble.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {
    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping("/list")
    public String homePage(Model model){
        model.addAttribute("cart",new CardItem());
        model.addAttribute("furnitures",furnitureService.getAllFurniture());
        return "furniture-list";
    }

    @GetMapping("/add")
    public String addFurniture(Model model){
        model.addAttribute("furniture",new Furniture());
        return "furniture-form";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable UUID id,Model model){
        model.addAttribute("furniture",furnitureService.getFurnitureById(id));
        return "furniture-form";
    }

    @PostMapping("/add")
    public String saveOrUpdate(@ModelAttribute Furniture furniture){
        furnitureService.saveOrUpdate(furniture);
        return "redirect:/furniture/list";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable UUID id){
        furnitureService.deletFurniture(id);
        return "redirect:/list";
    }
}
