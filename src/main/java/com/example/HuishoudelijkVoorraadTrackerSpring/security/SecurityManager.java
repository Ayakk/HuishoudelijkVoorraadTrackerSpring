package com.example.HuishoudelijkVoorraadTrackerSpring.security;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class SecurityManager {
    private static SecurityManager sm = new SecurityManager();
    private List<Account> allUsers = new ArrayList();

    public static SecurityManager getInstance() {
        return sm;
    }

    public boolean addUser(String username, String password, String role) {
        Account toAdd = new Account();
        toAdd.setUsername(username);
        toAdd.setUsername(password);
        toAdd.setUsername(role);
        if (!allUsers.contains(toAdd)) return allUsers.add(toAdd);
        return false;
    }

    public boolean registerUser(String username, String password, String role){
        return addUser(username, password, role);
    }

    public Account getUserByName(String uname) {
        for (Account u : allUsers) {
            if (u.getUsername().equals(uname)) return u;
        }
        return null;
    }
}