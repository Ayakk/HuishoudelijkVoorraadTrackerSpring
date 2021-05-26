package com.example.HuishoudelijkVoorraadTrackerSpring.services;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void save(Item item) {
        this.itemRepo.save(item);
    }

    public void delete(Item item) {
        this.itemRepo.delete(item);
    }

    public void update(Item item) {
        this.itemRepo.save(item);
    }

    public Optional<Item> getById(Long id) {
        return itemRepo.findById(id);
    }

    public Optional<Item> getByName(String name) {
        return itemRepo.findByName(name);
    }

    public List<Item> getAll() {
        return itemRepo.findAll();
    }

    public void deleteById(long id) {
        this.itemRepo.deleteById(id);
    }
}