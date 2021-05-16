package com.example.HuishoudelijkVoorraadTrackerSpring.entities;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.IInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements IInventory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;
    @Getter
    @Column(name = "items")
    @OneToMany
    private ArrayList<Item> inventory = new ArrayList<>();
}