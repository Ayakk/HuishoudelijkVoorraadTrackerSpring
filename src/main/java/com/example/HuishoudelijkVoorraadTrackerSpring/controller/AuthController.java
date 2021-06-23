package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.DRole;
import com.example.HuishoudelijkVoorraadTrackerSpring.dto.requests.LoginRequest;
import com.example.HuishoudelijkVoorraadTrackerSpring.dto.requests.signupRequest;
import com.example.HuishoudelijkVoorraadTrackerSpring.dto.responses.jwtResponse;
import com.example.HuishoudelijkVoorraadTrackerSpring.dto.responses.messageResponse;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Role;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.RoleRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.security.jwt.JwtUtils;
import com.example.HuishoudelijkVoorraadTrackerSpring.security.services.UserDetailsImpl;
import com.sun.el.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    InventoryRepo inventoryRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    private static final String ROLENOTFOUND = "Error: Role is not found.";

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        var inventoryProductsfromDB = "";
        String username = loginRequest.getUsername();
        String password = String.valueOf(loginRequest.getPassword());


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) //(item -> item.getAuthority()) <- oude code als dit breekt
                .collect(Collectors.toList());


        for (Inventory inventory : inventoryRepo.findAll()){
            if (inventory.getId().equals(userDetails.getId())){
                inventoryProductsfromDB = inventory.getProducts();
            }
        }

        log.info("Inventory products form db: {}", inventoryProductsfromDB);
        return ResponseEntity.ok(new jwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles,
                inventoryProductsfromDB
        ));

    }
    @PostMapping("/signout")
    public ResponseEntity<messageResponse> logoutUser() {
        Stream strRoles = null;
        antlr.collections.List roles = new antlr.collections.List() {
            @Override
            public void add(Object o) {

            }

            @Override
            public void append(Object o) {

            }

            @Override
            public Object elementAt(int i) throws NoSuchElementException {
                return null;
            }

            @Override
            public Enumeration elements() {
                return null;
            }

            @Override
            public boolean includes(Object o) {
                return false;
            }

            @Override
            public int length() {
                return 0;
            }
        };
        if (strRoles == null) {
            Role userRole = roleRepo.findByName(DRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLENOTFOUND));
            roles.add(userRole);
        } else {
            if ("admin".equals(roles)) {
                Role adminRole = roleRepo.findByName(DRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException(ROLENOTFOUND));
                roles.add(adminRole);
            } else {
                Role userRole = roleRepo.findByName(DRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException(ROLENOTFOUND));
                roles.add(userRole);
            }
        }
        return ResponseEntity.ok(new messageResponse("User logged out successfully!"));
//        return "Succesvol uitgelogd!";
    }

    @PostMapping("/changepwd")
    public ResponseEntity<?> changePassword(@Valid @RequestBody signupRequest signUpRequest) {
        for (Account account : accountRepo.findAll()){
            if (account.getUsername().equals(signUpRequest.getUsername())){
                account.setPassword(encoder.encode(signUpRequest.getPassword()));
                accountRepo.save(account);
            }
        }
        return ResponseEntity.ok(new messageResponse("Password changed succesfully!"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody signupRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        String password = String.valueOf(signUpRequest.getPassword());

        log.info("username: {}, password: {}", username, password);


        if (accountRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new messageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        Account user = new Account();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepo.findByName(DRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLENOTFOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepo.findByName(DRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ROLENOTFOUND));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepo.findByName(DRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ROLENOTFOUND));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        accountRepo.save(user);

        for(Account a : accountRepo.findAll()){
            if(a.getUsername().equals(user.getUsername())){
                Inventory i = new Inventory();
                i.setId(a.getId());
                i.setProducts("0,0;");
                inventoryRepo.save(i);
            }
        }
        return ResponseEntity.ok(new messageResponse("User registered successfully!"));
    }
}

