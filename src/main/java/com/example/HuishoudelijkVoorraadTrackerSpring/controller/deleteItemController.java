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
@RequestMapping("/deleteItem")
public class deleteItemController {
    @Autowired
    ItemService itemService;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String itemGet() {
        return "<html>\n" +
                "<head>\n" +
                "<script src=\"\"></script>\n" +
                "</head>\n" +
                "<header>" +
                "<title>Delete Item</title>" +
                "</header>\n" +
                "<body>\n" +
                "<h1>Delete item</h1>\n"+
                "<form id=\"deleteItemForm\" method=\"post\">\n" +
                "    <label for=\"id\">Welke item(Geef ID):</label><br>" +
                "    <input id=\"id\" placeholder=\"00\" name=\"id\" type=\"number\"><br>\n" +
                "    <input type=\"submit\" id=\"submitButton\" value=\"Submit\">\n" +
                "</form>\n" +
                "<h1>All items</h1>\n"+
                getItems()+
                "</body>\n" +
                "</html>";
    }

    @PostMapping()
    public String deleteitem(Item newItem) {
        System.out.println("ID:" + newItem.getId());
        for(Item oldItem : itemService.getAll()){
            if(oldItem.getId().equals(newItem.getId())){
                itemService.delete(oldItem);
            }
        }
        return "register_success";
    }

    public List<String> getItems(){
        List<Item> a = itemService.getAll();
        List<String> b = new ArrayList<String>();
        for(Item i : a){
            b.add("ID: "+ i.getId()+"Name: " + i.getName() + "\n" + "Description: " + i.getDescription() + "\n" + "Price: " + i.getPrice()) ;
        }
        return b;
    }
}
