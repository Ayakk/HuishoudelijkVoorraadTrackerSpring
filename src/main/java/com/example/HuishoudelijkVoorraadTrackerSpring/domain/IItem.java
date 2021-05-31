package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

public interface IItem {
    Long getId();
    String getName();
    String getDescription();
    double getPrice();
    int getQuantity();

    int getInventoryid();
    void setInventoryid(int inventoryid);

    void setId(Long id);
    void setName(String name);
    void setDescription(String description);
    void setPrice(double price);
    void setQuantity(int quantity);

}
