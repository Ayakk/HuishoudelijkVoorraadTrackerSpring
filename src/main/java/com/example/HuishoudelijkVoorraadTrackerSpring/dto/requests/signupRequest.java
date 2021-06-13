package com.example.HuishoudelijkVoorraadTrackerSpring.dto.requests;

import java.util.Set;

public class signupRequest {

    private String username;
    private CharSequence password;
    private Set<String> role;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    public void setRoles(Set<String> roles) {
        this.role = roles;
    }

    public String getUsername() {
        return this.username;
    }

    public CharSequence getPassword() {
        return this.password;
    }

    public Set<String> getRole() {
        return this.role;
    }
}

