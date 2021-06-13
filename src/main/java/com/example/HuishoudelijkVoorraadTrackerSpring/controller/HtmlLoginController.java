//package com.example.HuishoudelijkVoorraadTrackerSpring.controller;
//
//import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
//import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
//import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
//import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.impl.crypto.MacProvider;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.ws.rs.FormParam;
//import javax.ws.rs.Produces;
//import java.security.Key;
//import java.util.Calendar;
//import java.util.List;
//
//@RestController
//@Log4j2
//@RequestMapping("/loginHTML")
//public class HtmlLoginController {
//    public static final Key key = MacProvider.generateKey();
//    @Autowired
//    AccountRepo accountRepo;
//    @Autowired
//    InventoryRepo inventoryRepo;
//
//    @PostMapping()
//    @Produces(MediaType.TEXT_HTML_VALUE)
//    ResponseEntity<String> processRegister(@FormParam("username") String username, @FormParam("password") String password) {
//        Long id= Long.valueOf(0);
//        String inventoryProductsfromDB = "";
//        String token = "";
//
//        for(Account dbAccounts : accountRepo.findAll()){
//            if (username.equals(dbAccounts.getUsername())){
//                id = dbAccounts.getId();
//            }
//        }
//
//        for (Inventory inventory : inventoryRepo.findAll()){
//            if (inventory.getId().equals(id)){
//                inventoryProductsfromDB = inventory.getProducts();
//                System.out.println(inventoryProductsfromDB);
//            }
//        }
//
//        try {
//            String role = null;
//            for (Account u : accountRepo.findAll()) {
//                System.out.println(u.getUsername());
//                if (u.getUsername().equals(username) && u.checkPassword(password)) {
//                    role = "test";
//
//                }
//            }
//            if (role == null) throw new IllegalArgumentException("No user found!");
//
//            token = createtoken(username, role);
//            List<Account> allAccounts = accountRepo.findAll();
//            for (Account a : allAccounts) {
//                if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
//                    System.out.println("TOKEEEEEEEN" + token);
//                    String s = "<script>window.location = \"/viewStorage.html\"\n" +
//                            "sessionStorage.setItem(\"userID\", " + id + ")\n" +
//                            "sessionStorage.setItem(\"products\", \"" + inventoryProductsfromDB + "\")\n" +
//                            "sessionStorage.setItem(\"myJWT\", \"" + token + "\")\n" +
//                            "</script>";
//
//                    return new ResponseEntity<>(s, HttpStatus.OK);
//                }
//            }
//        } catch (JwtException | IllegalArgumentException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Login Failed!", HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity<>("Request handled", HttpStatus.OK);
//    }
//
//    private String createtoken(String username, String role) throws JwtException{
//        Calendar expiration = Calendar.getInstance();
//        expiration.add(Calendar.MINUTE, 30);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setExpiration(expiration.getTime())
//                .claim("role", role)
//                .signWith(SignatureAlgorithm.HS512, key)
//                .compact();
//    }
//
//}