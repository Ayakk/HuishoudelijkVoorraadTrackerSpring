package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;

@RestController
@Log4j2
@RequestMapping("/registerHTML")
public class htmlRegisterController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    InventoryRepo inventoryRepo;

    @PostMapping()
    ResponseEntity<String> createAccount(@FormParam("username") String username, @FormParam("password") String password){
        Account a = new Account();
        a.setUsername(username);
        a.setPassword(password);
        accountRepo.save(a);

        for(Account account : accountRepo.findAll()){
            if(account.getUsername().equals(username)){
                Inventory i = new Inventory();
                i.setId(account.getId());
                i.setProducts("28,0;");
                inventoryRepo.save(i);
            }
        }

        return new ResponseEntity<>("<script>window.location=\"index.html\"</script>", HttpStatus.OK);
    }
}