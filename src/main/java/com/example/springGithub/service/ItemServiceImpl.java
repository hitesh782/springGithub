package com.example.springGithub.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springGithub.model.Item;
import com.example.springGithub.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository ;
	
	 private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Override
	public ResponseEntity<List<Item>> getAllItems() {
		List<Item> items = itemRepository.findAll();
		return new ResponseEntity<>(items,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Item> getItemById(String itemId) {
		Optional<Item> itemOptional = itemRepository.findById(itemId);
		if(itemOptional.isPresent()) {
			logger.info("item is present with given id");
			return new ResponseEntity<>(itemOptional.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Item> createItem(Item item) {
		Item savedItem = itemRepository.save(item);
		return new ResponseEntity<>(savedItem,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Item> updateItem(String itemId, Item item) {
		Optional<Item> existingItemOptional = itemRepository.findById(itemId);
		if(existingItemOptional.isPresent()) {
			Item existing = existingItemOptional.get();
			existing.setName(item.getName());
			existing.setPrice(item.getPrice());
			itemRepository.save(existing);
			return new ResponseEntity<>(existing,HttpStatus.OK);
        }
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Void> deleteItem(String itemId) {
		if(itemRepository.existsById(itemId)) {
			itemRepository.deleteById(itemId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Page<Item> getItemsPaginationWise(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

}
