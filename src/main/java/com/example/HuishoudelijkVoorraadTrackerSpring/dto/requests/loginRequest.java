package com.example.HuishoudelijkVoorraadTrackerSpring.dto.requests;

import lombok.Getter;
import lombok.Setter;

public class loginRequest {
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
}
