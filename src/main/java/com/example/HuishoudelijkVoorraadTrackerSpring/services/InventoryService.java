package com.example.HuishoudelijkVoorraadTrackerSpring.services;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    public Map<Integer, Integer> getMap(Inventory inventory){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        String products = inventory.getProducts();
        String[] parts = products.split(";");
        for(String i : parts){
            String[] commaParts = i.split(",");
            map.put(Integer.parseInt(commaParts[0]), Integer.parseInt(commaParts[1]));
        }
        System.out.println("getMapFunction: "+map);

        return map;
    }
}