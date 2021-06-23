package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Role;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.RoleRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/test")
public class IntegratieTests {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    InventoryRepo inventoryRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    RoleRepo roleRepo;

    Map<Integer, Account> responseMapAccount = new HashMap<Integer, Account>();
    Map<Integer, Inventory> responseMapInventory = new HashMap<Integer, Inventory>();
    Map<Integer, Item> responseMapItem = new HashMap<Integer, Item>();
    Map<Integer, Role> responseMapRole = new HashMap<Integer, Role>();

    @GetMapping("/getAllAccount")
    public Map<Integer, Account> getAllAccount(){
        for (Account account : accountRepo.findAll()){
            responseMapAccount.put(Math.toIntExact(account.getId()), account);
        }
        return responseMapAccount;
    }

    @GetMapping("/getAllInventory")
    public Map<Integer, Inventory> getAllInventory(){
        for (Inventory inventory : inventoryRepo.findAll()){
            responseMapInventory.put(Math.toIntExact(inventory.getId()), inventory);
        }
        return responseMapInventory;
    }

    @GetMapping("/getAllItem")
    public Map<Integer, Item> getAllItem(){
        for (Item item : itemRepo.findAll()){
            responseMapItem.put(Math.toIntExact(item.getId()), item);
        }
        return responseMapItem;
    }

    @GetMapping("/getAllRole")
    public Map<Integer, Role> getAllRole(){
        for (Role role : roleRepo.findAll()){
            responseMapRole.put(Math.toIntExact(role.getId()), role);
        }
        return responseMapRole;
    }
}
