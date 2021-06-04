package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/loginHTML")
public class HtmlLoginController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    InventoryRepo inventoryRepo;

    @PostMapping()
    @Produces(MediaType.TEXT_HTML_VALUE)
    ResponseEntity<String> processRegister(@FormParam("username") String username, @FormParam("password") String password) {
        System.out.println("LOGIN USERNAME: "+username);
        System.out.println("LOGIN PASSWORD: "+password);

        Long id= Long.valueOf(0);
        String inventoryProductsfromDB = "";

        for(Account dbAccounts : accountRepo.findAll()){
            if (username.equals(dbAccounts.getUsername())){
                id = dbAccounts.getId();
            }
        }

        for (Inventory inventory : inventoryRepo.findAll()){
            if (inventory.getId().equals(id)){
                inventoryProductsfromDB = inventory.getProducts();
                System.out.println(inventoryProductsfromDB);
            }
        }

        System.out.println("ACCOUNT ID: " + id);
        List<Account> allAccounts = accountRepo.findAll();
        for (Account a : allAccounts) {
            System.out.println("");
            System.out.println("LOOP USERNAME: " + a.getUsername() + " COMPARED TO: " + username);
            System.out.println("LOOP PASSWORD: " + a.getPassword() + " COMPARED TO: "  + password);
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                System.out.println("SUCCESVOL AFGEROND INLOGGEN");

                String s = "<script>window.location = \"http://localhost:8080/viewStorage.html\"\n" +
                        "sessionStorage.setItem(\"userID\", "+ id +")\n"+
                        "sessionStorage.setItem(\"products\", \""+ inventoryProductsfromDB +"\")\n"+
                        "</script>";

                return new ResponseEntity<>(s, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Login Failed!", HttpStatus.EXPECTATION_FAILED);
    }

}