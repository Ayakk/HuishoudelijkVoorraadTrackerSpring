package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/createItem")
public class createItemController {
    @Autowired
    ItemRepo itemRepo;

    @PostMapping()
    public String createItem(Item item) {
        String nameReplace = item.getName();
        String descriptionReplace = item.getDescription();
        double priceReplace = item.getPrice();

        nameReplace.replace(",", "");
        descriptionReplace.replace(",", "");

        item.setName(nameReplace);
        item.setDescription(descriptionReplace);
        item.setPrice(priceReplace);

        System.out.println("ItemPage itemName: " + item.getName());
        System.out.println("ItemPage itemDscp: " + item.getDescription());
        System.out.println("ItemPage itemPrice: " + item.getPrice());
        itemRepo.save(item);
        return "register_success";
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String itemGet() {
        return "<html>\n" +
                "<head>\n" +
                "<script src=\"\"></script>\n" +
                "</head>\n" +
                "<header>" +
                "<title>Create Item</title>" +
                "</header>\n" +
                "<body>\n" +
                "<h1>Create new item</h1>\n" +
                "<form id=\"createItemForm\" method=\"post\">\n" +
                "    <label for=\"name\">Name:</label><br>" +
                "    <input id=\"name\" placeholder=\"Komkommer\" name=\"name\" type=\"text\"><br>\n" +

                "    <label for=\"description\">description:</label><br>\n" +
                "    <input id=\"description\" placeholder=\"Heel gezond\" name=\"description\" type=\"text\">\n" +

                "    <label for=\"price\">price:</label><br>\n" +
                "    <input id=\"price\" placeholder=\"10.00\" name=\"price\" type=\"number\">\n" +

                "    <input type=\"submit\" id=\"submitButton\" value=\"Submit\">\n" +
                "</form>\n" +
                "<h1>All items</h1>\n" +
                getItems() +
                "</body>\n" +
                "</html>";
    }

    public List<String> getItems() {
        List<Item> a = itemRepo.findAll();
        List<String> b = new ArrayList<String>();
        for (Item i : a) {
            b.add("ID: " + i.getId() + " Name: " + i.getName() + "\n" + " Description: " + i.getDescription() + "\n" + " Price: " + i.getPrice());
        }
        return b;
    }
}