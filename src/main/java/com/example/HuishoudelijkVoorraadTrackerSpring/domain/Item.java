package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

import lombok.Getter;
import lombok.Setter;

public class Item implements IItem{
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private double price;
}
