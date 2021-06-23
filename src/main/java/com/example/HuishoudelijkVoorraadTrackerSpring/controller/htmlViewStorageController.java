package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/htmlStorage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class htmlViewStorageController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    InventoryRepo inventoryRepo;
    @Autowired
    ItemRepo itemRepo;

    Map<Integer, String> mapForJS = new HashMap<Integer, String>();
    Map<Integer, Integer> mapForBackend = new HashMap<Integer, Integer>();
    Map<Integer, Item> mapForJS2 = new HashMap<Integer, Item>();

    @PostMapping("/deleteItem")
    public ResponseEntity<String> deleteItem(@RequestBody Item item){
        mapForBackend.clear();
        mapForJS.clear();
        mapForJS2.clear();

        log.info("function deleteItem");
        for(Inventory i : inventoryRepo.findAll()){
            int z = Math.toIntExact(i.getId());
            if(z == item.getInventoryid()){
                String s = i.getProducts();
                String toReplace = item.getId() + "," + item.getQuantity() + ";";
                String newString = s.replace(toReplace, "");
                i.setProducts(newString);
                inventoryRepo.save(i);
            }
        }
        return new ResponseEntity<>("Item deleted", HttpStatus.OK);
    }

    @PostMapping("/giveID")
    public ResponseEntity<String> getID(@RequestBody Inventory inventory){
        mapForBackend.clear();

        for (Inventory i : inventoryRepo.findAll()){
            if(i.getId() == inventory.getId()){
                String products = i.getProducts();
                String[] parts = products.split(";");
                for(String s : parts){
                    String[] commaParts = s.split(",");
                    mapForBackend.put(Integer.parseInt(commaParts[0]), Integer.parseInt(commaParts[1]));
                }
            }
        }
        return new ResponseEntity<>("ID returned", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(){
        mapForBackend.clear();
        mapForJS.clear();
        mapForJS2.clear();
        log.info("LOGOUT CHECK: \n {} \n {} \n {}", mapForBackend, mapForJS, mapForJS2);
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

    @GetMapping("/getInventory")
    public Map<Integer, String> getInventory(){
        mapForJS.clear();
        for(Inventory i : inventoryRepo.findAll()){
            mapForJS.put(Math.toIntExact(i.getId()), i.getProducts());
        }
        return mapForJS;
    }

    @GetMapping("/getItemData")
    public Map<Integer, Item> getItemData(){
        try{
            log.info("MAP1: \n {} \n {}", mapForJS2.get(31).getQuantity(), mapForJS2.get(32).getQuantity());
        }catch (Exception e){
            log.info(e);
        }
        return mapForJS2;
    }

    @PostMapping("/giveInventoryData")
    public Map<Integer, Item> giveInventoryData(@RequestBody Inventory inventory){
        String products = inventory.getProducts();
        String[] parts = products.split(";");
        for(String i : parts){
            String[] commaParts = i.split(",");
            mapForBackend.put(Integer.parseInt(commaParts[0]), Integer.parseInt(commaParts[1]));
        }

        //checks what items belong to the user
        List<Item> allItemsFromRepoList = itemRepo.findAll();
        for (Item i : allItemsFromRepoList) {
            int z = Math.toIntExact(i.getId());
            if(mapForBackend.containsKey(z)){
                i.setQuantity(mapForBackend.get(z));
                mapForJS2.put(z, i);
            }else{
                log.info("ITEM DOESNT BELONG TO THIS USER");
            }
        }
        try{
            log.info("MAP2: \n {} \n {}", mapForJS2.get(31).getQuantity(), mapForJS2.get(32).getQuantity());
        }catch (Exception e){
            log.info(e);
        }
        return mapForJS2;
    }

    @PostMapping("/updateAmounts")
    public ResponseEntity<String> updateAmount(@RequestBody Item item){
        int z = Math.toIntExact(item.getId());
        log.info("\n Function updateAmount \n Map before conversion: {}", mapForBackend);

        mapForBackend.remove(z);
        mapForBackend.put(z, item.getQuantity());

        String s = String.valueOf(mapForBackend);
        s = s.replace("{", "");
        s=s.replace("}", ";");
        s=s.replace(",", ";");
        s=s.replace("=", ",");
        s=s.replace(" ", "");

        log.info("\n Map after conversion: {}", s);
        Inventory inventory = new Inventory((long) item.getInventoryid(), s);
        inventoryRepo.save(inventory);
        return new ResponseEntity<>("Amount updated successfully", HttpStatus.OK);
    }
}