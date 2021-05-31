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
@RequestMapping("/updateItem")
public class updateItemController {
    @Autowired
    ItemRepo itemRepo;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String itemGet() {
        return "<html>\n" +
                "<head>\n" +
                "<script src=\"\"></script>\n" +
                "</head>\n" +
                "<header>" +
                "<title>Update Item</title>" +
                "</header>\n" +
                "<body>\n" +
                "<h1>Update item</h1>\n"+
                "<form id=\"updateItemForm\" method=\"post\">\n" +
                "    <label for=\"id\">Welke item(Geef ID):</label><br>" +
                "    <input id=\"id\" placeholder=\"00\" name=\"id\" type=\"number\"><br>\n" +

                "    <label for=\"name\">name:</label><br>" +
                "    <input id=\"name\" placeholder=\"Komkommer\" name=\"name\" type=\"text\"><br>\n" +

                "    <label for=\"description\">description:</label><br>\n" +
                "    <input id=\"description\" placeholder=\"Heel gezond\" name=\"description\" type=\"text\">\n" +

                "    <label for=\"price\">price:</label><br>\n" +
                "    <input id=\"price\" placeholder=\"10.00\" name=\"price\" type=\"number\">\n" +

                "    <input type=\"submit\" id=\"submitButton\" value=\"Submit\">\n" +
                "</form>\n" +
                "<h1>All items</h1>\n"+
                getItems()+
                "</body>\n" +
                "</html>";
    }

    @PostMapping()
    public String editItem(Item newItem) {
        System.out.println("ID:" + newItem.getId());
        for(Item oldItem : itemRepo.findAll()){
            if(oldItem.getId().equals(newItem.getId())){
                oldItem.setName(newItem.getName());
                oldItem.setDescription(newItem.getDescription());
                oldItem.setPrice(newItem.getPrice());
                itemRepo.save(oldItem);
            }
        }
        return "register_success";
    }

    public List<String> getItems(){
        List<Item> a = itemRepo.findAll();
        List<String> b = new ArrayList<String>();
        for(Item i : a){
            b.add("ID: "+ i.getId()+"Name: " + i.getName() + "\n" + "Description: " + i.getDescription() + "\n" + "Price: " + i.getPrice()) ;
        }
        return b;
    }
}
