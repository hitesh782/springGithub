package com.example.springGithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springGithub.model.Item;
import com.example.springGithub.repository.ItemRepository;
import com.example.springGithub.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/items")
@Validated
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	@GetMapping
	public ResponseEntity<List<Item>> getAllItems(){
		return itemService.getAllItems();
	}
	
	@GetMapping("/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable String itemId) {
		return itemService.getItemById(itemId);
	}
	
	@PostMapping
	public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
		return itemService.createItem(item);
	}
	
	@PutMapping("{itemId}")
	public ResponseEntity<Item> updateItem(@PathVariable String itemId, @RequestBody @Valid Item item) {
		return itemService.updateItem(itemId, item);
	}
	
	@DeleteMapping("{itemId}")
	public ResponseEntity<Void> deleteItemById(@PathVariable String itemId) {
		return itemService.deleteItem(itemId);
	}
	
	
	@GetMapping("/paginationWise")
	public Page<Item> getItemByPagination(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="2") int size){
		
		PageRequest pageable = PageRequest.of(page, size);
		return itemService.getItemsPaginationWise(pageable);
	}
	
}
