package com.example.HuishoudelijkVoorraadTrackerSpring.dto.responses;


import java.util.List;

public class jwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;
    private String products;

    public jwtResponse(String accessToken, Long id, String username, List<String> roles, String products) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.products = products;
    }

    public String getProducts() {
        return products;
    }
    public void setProducts(String products) {
        this.products = products;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
