package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String deleteItem(@RequestBody Item item){
        mapForBackend.clear();
        mapForJS.clear();
        mapForJS2.clear();

        System.out.println("Function deleteItem");
        for(Inventory i : inventoryRepo.findAll()){
            System.out.println("LOOP");
            int z = Math.toIntExact(i.getId());
            if(z == item.getInventoryid()){
                System.out.println("IF");
                String s = i.getProducts();
                String toReplace = item.getId() + "," + item.getQuantity() + ";";
                System.out.println(s + " + " + toReplace);
                String newString = s.replace(toReplace, "");
                System.out.println("S REPLACED " + newString);
                i.setProducts(newString);
                inventoryRepo.save(i);
            }
        }
        return "";
    }

    @PostMapping("/giveID")
    public String getID(@RequestBody Inventory inventory){
        mapForBackend.clear();

        System.out.println("getID inventoryID: " + inventory.getId());

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
        return "";
    }

    @PostMapping("/logout")
    public String logOut(){
        mapForBackend.clear();
        mapForJS.clear();
        mapForJS2.clear();
        System.out.println("LOGOUT CHECK : \n" + mapForBackend + "\n" + mapForJS + "\n" + mapForJS2 + "\n");
        return "";
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
            System.out.println("MAP1 : " + mapForJS2.get(31).getQuantity() + " " + mapForJS2.get(32).getQuantity());
        }catch (Exception e){
            System.out.println(e);
        }
        return mapForJS2;
    }

    @PostMapping("/giveInventoryData")
    public Map<Integer, Item> giveInventoryData(@RequestBody Inventory inventory){
        System.out.println("FUNCTION getItemData IN htmlViewStorageController.java; INVENTORY ID : " + inventory.getId());
        System.out.println("FUNCTION getItemData AMOUNT IN htmlViewStorageController.java; INVENTORY PRODUCTS : " + inventory.getProducts());

        String products = inventory.getProducts();
        String[] parts = products.split(";");
        for(String i : parts){
            String[] commaParts = i.split(",");
            mapForBackend.put(Integer.parseInt(commaParts[0]), Integer.parseInt(commaParts[1]));
        }

        System.out.println("FUNCTION getItemData IN htmlViewStorageController.java; mapForBackend HASHMAP CONTENTS: "+mapForBackend);
        List<Item> allItemsFromRepoList = itemRepo.findAll();
        for (Item i : allItemsFromRepoList) {
            int z = Math.toIntExact(i.getId());
            if(mapForBackend.containsKey(z)){
                i.setQuantity(mapForBackend.get(z));
                mapForJS2.put(z, i);
            }else{
                System.out.println("ANSWER FALSE");
            }
        }
        try{
            System.out.println("MAP2 : " + mapForJS2.get(31).getQuantity() + " " + mapForJS2.get(32).getQuantity());
        }catch (Exception e){
            System.out.println(e);
        }
        return mapForJS2;
    }

    @PostMapping("/updateAmounts")
    public String updateAmount(@RequestBody Item item){
        int z = Math.toIntExact(item.getId());
        System.out.println("MAP BEFORE CONVERSION TO DB MODEL: " + mapForBackend);

        mapForBackend.remove(z);
        mapForBackend.put(z, item.getQuantity());

        String s = String.valueOf(mapForBackend);
        s = s.replace("{", "");
        s=s.replace("}", ";");
        s=s.replace(",", ";");
        s=s.replace("=", ",");
        s=s.replace(" ", "");

        System.out.println("MAP AFTER CONVERSION TO DB MODEL: " + s);
        Inventory inventory = new Inventory((long) item.getInventoryid(), s);
        inventoryRepo.save(inventory);
        return "";
    }
}