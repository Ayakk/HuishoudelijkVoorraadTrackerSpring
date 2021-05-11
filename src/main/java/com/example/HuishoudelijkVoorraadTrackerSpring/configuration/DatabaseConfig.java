package com.example.HuishoudelijkVoorraadTrackerSpring.configuration;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class DatabaseConfig
{
    @Bean
    CommandLineRunner initDatabase(AccountRepo accountRepo){
        return args -> {
            // hier wordt speler piet aangemaakt als die er nog niet is
            var user = accountRepo.findByUsername("username1").orElse(null);

            if (user == null){
                System.out.println("speler bestaat niet,de speler wordt aangemaakt");
                Account newAccount = new Account();
                newAccount.setId(1L);
                newAccount.setUsername("username1");
                newAccount.setPassword("pwd");
                newAccount.setRole("admin");

                accountRepo.save(newAccount);
            }
        };
    }
}
