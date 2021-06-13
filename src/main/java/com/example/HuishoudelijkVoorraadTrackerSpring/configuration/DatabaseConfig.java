package com.example.HuishoudelijkVoorraadTrackerSpring.configuration;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@Log4j2
public class DatabaseConfig {
    @Autowired
    PasswordEncoder encoder;

//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(dbUrl);
//        return new HikariDataSource(config);
//    }

    @Bean
    CommandLineRunner initDatabase(AccountRepo accountRepo, InventoryRepo inventoryRepo, ItemRepo itemRepo){
        return args -> {
            // hier wordt een nieuwe user aangemaakt als die nog niet bestaat.
            var user = accountRepo.findByUsername("username1").orElse(null);

            if (user == null){
                System.out.println("speler bestaat niet,de speler wordt aangemaakt");
                Account newAccount = new Account();
                newAccount.setUsername("username1");
                newAccount.setPassword(encoder.encode("pwd"));
                accountRepo.save(newAccount);

                Inventory i = new Inventory();
                i.setId(accountRepo.findByUsername("username1").get().getId());
                i.setProducts("0,0;");
                inventoryRepo.save(i);
            }


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
                newItem.setInventoryid(0);
                itemRepo.save(newItem);
            }
            if(Melk == null){
                System.out.println("Melk bestaat niet,de item wordt aangemaakt");
                Item newItem = new Item();
                newItem.setDescription("Pak melk");
                newItem.setName("Melk");
                newItem.setPrice(1.99);
                newItem.setQuantity(1);
                newItem.setInventoryid(0);
                itemRepo.save(newItem);
            }
            if(Banaan == null){
                System.out.println("Banaan bestaat niet,de item wordt aangemaakt");
                Item newItem = new Item();
                newItem.setDescription("Tros banaantjes");
                newItem.setName("Banaan");
                newItem.setPrice(4.99);
                newItem.setQuantity(1);
                newItem.setInventoryid(0);
                itemRepo.save(newItem);
            }

        };
    }
}
