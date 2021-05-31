package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

import lombok.Getter;
import lombok.Setter;

public class Inventory implements IInventory{
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String products;
}
