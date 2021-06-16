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

import java.util.ArrayList;
import java.util.List;

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
	public void checkIfInventoryExists(){
		for(Inventory inventory : inventoryRepo.findAll()){
			int z = Math.toIntExact(inventory.getId());
			assertNotNull(z);
		}
	}

	@Test
	public void checkIfAccountExists(){
		for(Account account : accountRepo.findAll()){
			int z = Math.toIntExact(account.getId());
			assertNotNull(z);
		}
	}

	@Test
	public void checkIfItemExists(){
		for(Item item : itemRepo.findAll()){
			int z = Math.toIntExact(item.getId());
			assertNotNull(z);
		}
	}

	@Test
	public void	checkIfAccountsHasInventory(){
		List<Long> accountIds = new ArrayList<>();
		for (Account account : accountRepo.findAll()){
			accountIds.add(account.getId());
		}
		for (Inventory inventory : inventoryRepo.findAll()){
			assertTrue(accountIds.contains(inventory.getId()));
		}
	}
}
