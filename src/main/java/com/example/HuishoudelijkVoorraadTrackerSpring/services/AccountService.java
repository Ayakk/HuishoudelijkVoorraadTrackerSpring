package com.example.HuishoudelijkVoorraadTrackerSpring.services;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void save(Account account) {
        this.accountRepo.save(account);
    }

    public void delete(Account account) {
        this.accountRepo.delete(account);
    }

    public void update(Account account) {
        this.accountRepo.save(account);
    }

    public Optional<Account> getById(Long id) {
        return accountRepo.findById(id);
    }

    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    public void deleteById(long id) {
        this.accountRepo.deleteById(id);
    }
}