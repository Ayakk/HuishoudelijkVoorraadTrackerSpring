package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Inventory implements IInventory{
    @Getter @Setter
    private Long id;
    @Getter
    private ArrayList<Item> inventory;
}
