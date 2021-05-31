package com.example.HuishoudelijkVoorraadTrackerSpring.configuration;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class DatabaseConfig
{
    @Bean
    CommandLineRunner initDatabase(AccountRepo accountRepo, InventoryRepo inventoryRepo, ItemRepo itemRepo){
        return args -> {
            // hier wordt een nieuwe user aangemaakt als die nog niet bestaat.
            var user = accountRepo.findByUsername("username1").orElse(null);

            if (user == null){
                System.out.println("speler bestaat niet,de speler wordt aangemaakt");
                Account newAccount = new Account();
                newAccount.setUsername("username1");
                newAccount.setPassword("pwd");
                newAccount.setRole("admin");

                accountRepo.save(newAccount);
            }

//            // hier wordt een nieuwe inventory aangemaakt als die nog niet bestaat
//            var inventory = inventoryRepo.findById(1L).orElse(null);
//
//            if (inventory == null){
//                System.out.println("inventory bestaat niet,de inventory wordt aangemaakt");
//                Inventory newInventory = new Inventory();
//                Item i = new Item();
//                newInventory.getInventoryList().add(i);
//                inventoryRepo.save(newInventory);
//            }



            // hier wordt een nieuwe item aangemaakt als die nog niet bestaat
            var Appel = itemRepo.findByName("Appel").orElse(null);
            var Melk = itemRepo.findByName("Melk").orElse(null);
            var Banaan = itemRepo.findByName("Banaan").orElse(null);

            if (Appel == null){
                System.out.println("Appel bestaat niet,de item wordt aangemaakt");
                Item newItem = new Item();
                newItem.setDescription("Lekker appeltje");
                newItem.setName("Appel");
                newItem.setPrice(1);
                newItem.setQuantity(1);
//                newItem.setInventoryID(0);
                itemRepo.save(newItem);
            }
            if(Melk == null){
                System.out.println("Melk bestaat niet,de item wordt aangemaakt");
                Item newItem = new Item();
                newItem.setDescription("Pak melk");
                newItem.setName("Melk");
                newItem.setPrice(1.99);
                newItem.setQuantity(1);
//                newItem.setInventoryID(0);
                itemRepo.save(newItem);
            }
            if(Banaan == null){
                System.out.println("Banaan bestaat niet,de item wordt aangemaakt");
                Item newItem = new Item();
                newItem.setDescription("Tros banaantjes");
                newItem.setName("Banaan");
                newItem.setPrice(4.99);
                newItem.setQuantity(1);
//                newItem.setInventoryID(0);
                itemRepo.save(newItem);
            }

        };
    }
}
