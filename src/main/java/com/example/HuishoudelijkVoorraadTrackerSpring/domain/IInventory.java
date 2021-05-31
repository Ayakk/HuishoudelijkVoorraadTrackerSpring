package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

public interface IInventory {
    Long getId();
    String getProducts();

    void setId(Long id);
    void setProducts(String products);

}
