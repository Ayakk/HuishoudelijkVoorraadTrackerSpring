package com.example.HuishoudelijkVoorraadTrackerSpring.entities;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.IItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item implements IItem {
    @Id
    @GeneratedValue
    @Column(name="id")
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @Column(name="name")
    private String name;
    @Getter @Setter
    @Column(name="description")
    private String description;
    @Getter @Setter
    @Column(name="price")
    private double price;
    @Getter @Setter
    @Column(name="quantity")
    private int quantity;
}
