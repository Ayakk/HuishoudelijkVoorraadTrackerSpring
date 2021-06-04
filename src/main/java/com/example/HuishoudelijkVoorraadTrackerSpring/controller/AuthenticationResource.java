package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import java.security.Key;
import java.util.Calendar;

@PermitAll
@RestController
@Log4j2
@RequestMapping("/authentication")
public class AuthenticationResource {
    public static final Key key = MacProvider.generateKey();
    @Autowired
    AccountRepo accountRepo;

    @PostMapping()
    @Produces(MediaType.TEXT_HTML_VALUE)
    ResponseEntity<String> authenticateUser(@RequestBody Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        try {
            String role = null;
            for (Account u : accountRepo.findAll()) {
                if (u.getUsername().equals(username) && u.checkPassword(password)) {
                    role = u.getRole();

                }
            }
            if (role==null) throw new IllegalArgumentException("No user found!");

            String token = createtoken(username, role);
            String s = "<script>window.location = \"/viewStorage.html\"\n" +
                    "sessionStorage.setItem(\"myJWT\", "+ token +")\n"+
                    "</script>";

            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private String createtoken(String username, String role) throws JwtException{
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}