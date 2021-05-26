package com.example.HuishoudelijkVoorraadTrackerSpring.domain;

import lombok.Getter;
import lombok.Setter;


public class Account implements IAccount{
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String role;
}
