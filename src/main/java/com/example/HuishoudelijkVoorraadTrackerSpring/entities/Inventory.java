package com.example.HuishoudelijkVoorraadTrackerSpring.entities;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.IInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements IInventory {
    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @Column(name = "products")
    private String products;

    public com.example.HuishoudelijkVoorraadTrackerSpring.domain.Inventory convertToDomain(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, com.example.HuishoudelijkVoorraadTrackerSpring.domain.Inventory.class);
    }
}