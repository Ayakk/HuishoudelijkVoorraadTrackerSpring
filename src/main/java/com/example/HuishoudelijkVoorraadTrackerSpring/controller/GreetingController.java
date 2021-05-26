package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

//    @GetMapping("/index")
//    public String greetingForm(Model model) {
//        model.addAttribute("account", new Account());
//        return "account";
//    }
//
//    @PostMapping("/index")
//    public String greetingSubmit(@ModelAttribute Account account, Model model) {
//        model.addAttribute("account", account);
//        return "result";
//    }
}
