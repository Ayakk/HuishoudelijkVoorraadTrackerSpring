package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/createItem")
public class htmlAddItemController {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    InventoryRepo inventoryRepo;

    Map<Integer, Item> mapForJS = new HashMap<Integer, Item>();

    @GetMapping("/getAllItems")
    public Map<Integer, Item> getItems(){
        for (Item i : itemRepo.findAll()){
            mapForJS.put(Math.toIntExact(i.getId()), i);
        }
        return mapForJS;
    }

    @PostMapping("/postItem")
    public String createItem(@RequestBody Item item ) {
        itemRepo.save(item);

        for(Inventory inventory : inventoryRepo.findAll()){
            if(inventory.getId().equals(item.getId())){
                String s = inventory.getProducts();
                for(Item item1 : itemRepo.findAll()){
                    if(item1.getName().equals(item.getName())){
                        s+=item1.getId()+",0;";
                        inventory.setProducts(s);
                        inventoryRepo.save(inventory);
                    }
                }
            }
        }
        return "register_success";
    }
}