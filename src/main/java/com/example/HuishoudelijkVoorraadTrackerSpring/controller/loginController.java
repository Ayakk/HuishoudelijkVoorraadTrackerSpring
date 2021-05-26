package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.services.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/register")
public class loginController {
    @Autowired
    AccountService accountService;

    @PostMapping()
    public String processRegister(Account account) {
        String usernameReplace = account.getUsername();
        String passwordReplace = account.getPassword();
        String roleReplace = account.getRole();
        usernameReplace.replace(",", "");
        passwordReplace.replace(",", "");
        roleReplace.replace(",", "");
        account.setUsername(usernameReplace);
        account.setPassword(passwordReplace);
        account.setRole(roleReplace);
        System.out.println("LoginPage username: "+account.getUsername());
        System.out.println("LoginPage password: "+account.getPassword());
        System.out.println("LoginPage role: "+account.getRole());
        accountService.save(account);
        return "register_success";
    }

    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public String loginGet() {
        return "<html>\n" +
                "<head>\n" +
                "<script src=\"/index.js\"></script>\n"+
                "</head>\n"+
                "<header>" +
                "<title>WelcomeTest</title>" +
                "</header>\n" +
                "<body>\n" +
                "<form id=\"loginform\" method=\"post\">\n" +
                "    <label for=\"username\">username:</label><br>\n" +
                "    <input id=\"username\" placeholder=\"username1\" name=\"username\" type=\"text\"><br>\n" +
                "    <label for=\"password\">password:</label><br>\n" +
                "    <input id=\"password\" placeholder=\"pwd\" name=\"password\" type=\"password\">\n" +
                "    <label for=\"role\">role:</label><br>\n" +
                "    <input id=\"role\" placeholder=\"admin\" name=\"role\" type=\"text\">\n" +
                "    <input type=\"submit\" id=\"submitButton\" value=\"Submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
    }

//
//    //http://localhost:8080/login/{username}/{password}
//    @PostMapping("/{username}/{password}")
//    @Produces(MediaType.TEXT_HTML_VALUE)
//    public String loginPost(@PathVariable("username") String username, @PathVariable("password") String password){
//        System.out.println("FORMDATA: "+username);
//        System.out.println("FORMDATA: "+password);
//        for (Account a : accountService.getAll()) {
//            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
//                return "<html>\n" +
//                        "<header>" +
//                        "<title>WelcomeTest</title>" +
//                        "</header>\n" +
//                        "<body>\n" +
//                        "window.location = \"http://localhost:8080/home\"\n"+
//                        "</body>\n" +
//                        "</html>";
//            } else{
//                return "OEPSIE";
//            }
//        }
//        return "end post";
//    }
//
////    @PostMapping("/login")
////    public void loginPost(@RequestBody Account account) {
////        System.out.println(account.getUsername());
////        System.out.println(account.getPassword());
////    }


}
