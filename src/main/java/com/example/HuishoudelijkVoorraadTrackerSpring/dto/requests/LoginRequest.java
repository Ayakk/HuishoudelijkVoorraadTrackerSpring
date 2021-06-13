package com.example.HuishoudelijkVoorraadTrackerSpring.dto.requests;

public class LoginRequest {
    private String username;
    private  String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public Object getPassword() {
        return this.password;
    }
}

