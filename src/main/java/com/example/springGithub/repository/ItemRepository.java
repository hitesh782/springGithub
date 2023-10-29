package com.example.springGithub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springGithub.model.Item;

public interface ItemRepository extends MongoRepository<Item,String> {

}
