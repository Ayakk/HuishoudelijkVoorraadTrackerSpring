package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.services.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/index")
@Log4j2
public class loginController {
    @Autowired
    AccountService accountService;

    @PostMapping("/index")
    public String loginPost(@PathVariable String username, @PathVariable String password){
        System.out.println("FORMDATA: "+username);
        System.out.println("FORMDATA: "+password);
        for (Account a : accountService.getAll()) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return "Login succesvol";
            } else{
                return "OEPSIE";
            }
        }
        return "end post";
    }
}
