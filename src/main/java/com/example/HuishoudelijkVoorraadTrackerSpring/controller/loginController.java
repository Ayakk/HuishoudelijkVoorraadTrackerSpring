package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.services.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;

@RestController
@Log4j2
@RequestMapping("/login")
public class loginController {
    @Autowired
    AccountService accountService;

    @PostMapping()
    @Produces(MediaType.TEXT_HTML_VALUE)
    public String processRegister(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        System.out.println("Username LoginC: " + username);
        System.out.println("Username passwordC: " + password);

        for (Account a : accountService.getAll()) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return "<html>\n" +
                        "<header>" +
                        "<title>WelcomeTest</title>" +
                        "</header>\n" +
                        "<body>\n" +
                        "<script>window.location = \"http://localhost:8080/viewStorage\"</script>" +
                        "</body>\n" +
                        "</html>";
            } else {
                return
                        "<html>"
                        ;
            }
        }
        return null;
    }

    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public String loginGet() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"https://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <title>Getting Started: Handling Form Submission</title>\n" +
                "    <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\"/>\n" +
                "    <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                "    <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"></script>\n" +
                "    <script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n" +
                "    <script src=\"index.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"sidenav\">\n" +
                "    <div class=\"login-main-text\">\n" +
                "        <h2>Application<br> Login Page</h2>\n" +
                "        <p>Login or register from here to access.</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<div class=\"main\">\n" +
                "    <div class=\"col-md-6 col-sm-12\">\n" +
                "        <div class=\"login-form\">\n" +
                "            <form id=\"loginform\" method=\"post\">\n" +
                "                <div class=\"form-group\">\n" +
                "                    <label for=\"username\">username:</label>\n" +
                "                    <input id=\"username\" class=\"form-control\" placeholder=\"username1\" name=\"username\" type=\"text\">\n" +
                "                </div>\n" +
                "                <div class=\"form-group\">\n" +
                "                    <label for=\"password\">Password</label>\n" +
                "                    <input type=\"password\" id=\"password\" class=\"form-control\" name=\"password\" placeholder=\"pwd\">\n" +
                "                </div>\n" +
                "                <button type=\"submit\" id=\"submitButton\" class=\"btn btn-black\" value=\"Submit\">Login</button>\n" +
                "            </form>\n" +
                "                <br><button onclick=\"registerButtonClick()\" id=\"registerButton\" class=\"btn btn-secondary\">Register</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}