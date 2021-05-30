package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.services.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/viewStorage")
public class viewStorageController {
    @Autowired
    ItemService itemService;

    @PostMapping()
    public String setAmount() {
        return "Succes";
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String itemGet() {
        return "<html>\n" +
                "<head>\n" +
                "<script src=\"viewStorage.js\"></script>\n" +
                "</head>\n" +
                "<header>" +
                "<title>Create Item</title>" +
                "</header>\n" +
                "<body>\n" +
                "<h1>All items</h1>\n" +
                getItems() +
                "</body>\n" +
                "</html>";
    }

    public String getItems() {
        List<Item> a = itemService.getAll();
        List<String> b = new ArrayList<String>();
        for (Item i : a) {
            b.add(
                    "ID: " + i.getId() +
                    " | "+
                    " Name: " + i.getName() +
                    " | " +
                    " Description: " + i.getDescription() +
                    " | " +
                    " Price: " + i.getPrice() +
                    " | " +
                    "<input id=\"INPUT" + i.getId() + "\" placeholder=\"0\" type=\"number\">"+
                    "<button onClick=\"saveButtonFunc(this.id)\" id=\"BUTTON" + i.getId() +"\">Opslaan</button>" +
                    "<button onClick=\"deleteButtonFunc(this.id)\" id=\"DELBUTTON" + i.getId() + "\">Verwijderen</button>" +
                    "<br>"
            );
        }
        String returnString = String.join(",", b);
        return returnString;
    }
}