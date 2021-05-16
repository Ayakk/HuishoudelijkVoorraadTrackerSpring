package com.example.HuishoudelijkVoorraadTrackerSpring.services;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public void save(Inventory inventory) {
        this.inventoryRepo.save(inventory);
    }

    public void delete(Inventory inventory) {
        this.inventoryRepo.delete(inventory);
    }

    public void update(Inventory inventory) {
        this.inventoryRepo.save(inventory);
    }

    public Optional<Inventory> getById(Long id) {
        return inventoryRepo.findById(id);
    }

    public List<Inventory> getAll() {
        return inventoryRepo.findAll();
    }

    public void deleteById(long id) {
        this.inventoryRepo.deleteById(id);
    }
}