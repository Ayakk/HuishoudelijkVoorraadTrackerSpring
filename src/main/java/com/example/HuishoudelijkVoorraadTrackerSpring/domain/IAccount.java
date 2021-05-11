package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

public interface IAccount {
    Long getId();
    String getUsername();
    String getPassword();
    String getRole();

    void setId(Long id);
    void setUsername(String username);
    void setPassword(String password);
    void setRole(String role);
}
