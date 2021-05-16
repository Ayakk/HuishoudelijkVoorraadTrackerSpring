package com.example.HuishoudelijkVoorraadTrackerSpring.repositories;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
}
