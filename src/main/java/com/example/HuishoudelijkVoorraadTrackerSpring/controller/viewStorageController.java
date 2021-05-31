package com.example.HuishoudelijkVoorraadTrackerSpring.controller;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/viewStorage")
public class viewStorageController {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    InventoryRepo inventoryRepo;

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    @PostMapping()
    public String setAmount() {
        System.out.println("test");
        return "Succes";
    }

    @PostMapping("/updateAmounts")
    public String updateAmount(@RequestBody Item item){
        System.out.println("Received Item ID: " + item.getId() + "\nReceived Amount: " + item.getQuantity() + "\nReceived inventoryID: " + item.getInventoryid());
        int z = Math.toIntExact(item.getId());
        System.out.println("MAP BEFORE: " + map);
        map.remove(z);
        map.put(z, item.getQuantity());

        String s = String.valueOf(map);
        s = s.replace("{", "");
        s=s.replace("}", ";");
        s=s.replace(",", ";");
        s=s.replace("=", ",");
        s=s.replace(" ", "");


        System.out.println("MAP AFTER: " + s);
        System.out.println(item.getInventoryid());
        System.out.println((long) item.getInventoryid());
        Inventory inventory = new Inventory((long) item.getInventoryid(), s);
        inventoryRepo.save(inventory);

        return "";
    }

    @PostMapping("/getProducts")
    public String getInventoryProducts(@RequestBody Inventory inventory){
        System.out.println("TEST2");
        System.out.println("INVENTORY ID: "+inventory.getId());
        System.out.println(inventory.getProducts());
        String products = inventory.getProducts();
        String[] parts = products.split(";");
        for(String i : parts){
            String[] commaParts = i.split(",");
            map.put(Integer.parseInt(commaParts[0]), Integer.parseInt(commaParts[1]));
        }
        System.out.println(map);

        return "";
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
        List<Item> a = itemRepo.findAll();
        List<String> b = new ArrayList<String>();
        System.out.println("MAP INHOUD: " + map);
        for (Item i : a) {
            int z = Math.toIntExact(i.getId());
            if(map.containsKey(z)){
                System.out.println(map.get(z));
                System.out.println("Answer TRUE");
                b.add(
                        "ID: " + i.getId() +
                                " | " +
                                " Name: " + i.getName() +
                                " | " +
                                " Description: " + i.getDescription() +
                                " | " +
                                " Price: " + i.getPrice() +
                                " | " +
                                "<input id=\"INPUT" + i.getId() + "\" placeholder=" + i.getQuantity() + " type=\"number\">" +
                                "<button onClick=\"saveButtonFunc(this.id)\" id=\"BUTTON" + i.getId() + "\">Opslaan</button>" +
                                "<button onClick=\"deleteButtonFunc(this.id)\" id=\"DELBUTTON" + i.getId() + "\">Verwijderen</button>" +
                                "<br>"
                );
            }else{
                System.out.println("ANSWER FALSE");
            }
        }
        String returnString = String.join(",", b);
        return returnString;
    }
}