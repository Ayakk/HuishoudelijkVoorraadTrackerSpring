package com.example.HuishoudelijkVoorraadTrackerSpring;

import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Account;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Inventory;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Item;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.AccountRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.InventoryRepo;
import com.example.HuishoudelijkVoorraadTrackerSpring.repositories.ItemRepo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HuishoudelijkVoorraadTrackerSpringApplicationTests {
	@Autowired
	InventoryRepo inventoryRepo;
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	ItemRepo itemRepo;

	@BeforeEach
	void contextLoads() {
		Account account = new Account();
		account.setId(Long.valueOf(1));
		account.setUsername("TesterAccount");
		account.setPassword("Password");

		Item item = new Item();
		item.setName("TestItem");
		item.setDescription("TestDescription");
		item.setPrice(5);
		item.setQuantity(1);
		item.setId(Long.valueOf(1));
	}

	@Test
	public void checkInventoryRepo(){
		for(Inventory i : inventoryRepo.findAll()){
			int z = Math.toIntExact(i.getId());
			assertEquals(z, 2);
		}
	}
}
