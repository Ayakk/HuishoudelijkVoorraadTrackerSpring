package com.example.HuishoudelijkVoorraadTrackerSpring.repositories;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
