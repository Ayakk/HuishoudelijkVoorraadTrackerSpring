package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

import java.util.ArrayList;

public interface IInventory {
    Long getId();
    ArrayList<Object> getInventory();

    void setId(Long id);
}
