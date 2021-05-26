package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import org.springframework.web.bind.annotation.PutMapping;

public class updateItemController {
    @PutMapping("/update")
    public String editItem(Item newItem) {
        System.out.println("ID:" + newItem.getId());
        for(Item oldItem : itemService.getAll()){
            if(oldItem.getId().equals(newItem.getId())){
                oldItem.setName(newItem.getName());
                oldItem.setDescription(newItem.getDescription());
                oldItem.setPrice(newItem.getPrice());
                itemService.update(oldItem);
            }
        }
        return "register_success";
    }
}
