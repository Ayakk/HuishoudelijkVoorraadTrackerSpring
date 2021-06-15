package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/createItem")
@PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PostMapping()
    ResponseEntity<String> addExistingItem(@RequestBody Item item ){
        System.out.println(item.getInventoryid());
        System.out.println(item.getId());

        Long inventoryIDtoLong = (long) item.getInventoryid();
        for(Inventory inventory : inventoryRepo.findAll()){
            if(inventory.getId().equals(inventoryIDtoLong)){
                String s = inventory.getProducts();
                for(Item item1 : itemRepo.findAll()){
                    if(item1.getId().equals(item.getId())){
                        s+=item1.getId()+",0;";
                        inventory.setProducts(s);
                        inventoryRepo.save(inventory);
                    }
                }
            }
        }
        return new ResponseEntity<>("Item added!", HttpStatus.OK);
    }


    @PostMapping("/postItem")
    ResponseEntity<String> createItem(@RequestBody Item item ) {
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
        return new ResponseEntity<>("Item added!", HttpStatus.OK);
    }
}