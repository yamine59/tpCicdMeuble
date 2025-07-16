package org.example.tpmeuble.service;

import org.example.tpmeuble.entity.Furniture;
import org.example.tpmeuble.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FurnitureService {
    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public void saveOrUpdate(Furniture furniture){
        furnitureRepository.save(furniture);
    }

    public List<Furniture> getAllFurniture(){
        return furnitureRepository.findAll();
    }

    public Furniture getFurnitureById(UUID id){
        return furnitureRepository.findById(id).orElse(null);
    }
    public void deletFurniture(UUID id){
        furnitureRepository.deleteById(id);
    }
}
