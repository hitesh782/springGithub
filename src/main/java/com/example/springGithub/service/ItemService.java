package com.example.springGithub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.springGithub.model.Item;

public interface ItemService {
	ResponseEntity<List<Item>> getAllItems();
	Page<Item> getItemsPaginationWise(Pageable pageable);
	
	ResponseEntity<Item> getItemById(String itemId);
	ResponseEntity<Item> createItem(Item item);
	ResponseEntity<Item> updateItem(String itemId, Item item);
	ResponseEntity<Void> deleteItem(String itemId);
}
