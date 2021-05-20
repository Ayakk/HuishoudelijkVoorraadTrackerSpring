package com.example.HuishoudelijkVoorraadTrackerSpring.entities;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.IInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "ListInventory")
    private List<Item> inventoryList;


    public List<Item> getInventory(){
        return inventoryList;
    }

    public Inventory(List<Item> inventoryList){
        inventoryList.add(new Item());
    }

    public com.example.HuishoudelijkVoorraadTrackerSpring.domain.Inventory convertToDomain(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, com.example.HuishoudelijkVoorraadTrackerSpring.domain.Inventory.class);
    }
}