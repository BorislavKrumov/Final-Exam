package com.academy.supermarket.controller;

import com.academy.supermarket.model.entity.Supermarket;
import com.academy.supermarket.model.util.AddItemsParams;
import com.academy.supermarket.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/supermarket")
public class SupermarketController {

    private SupermarketService supermarketService;

    @Autowired
    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @PostMapping
    public ResponseEntity<Supermarket> createSupermarket(@RequestBody @Valid Supermarket supermarket) {
        return new ResponseEntity<>(supermarketService.createSupermarket(supermarket), HttpStatus.CREATED);
    }

    @PostMapping("/addItems")
    public ResponseEntity<Supermarket> addItemsToSupermarket(@RequestBody @Valid AddItemsParams addItemsParams) {
        return new ResponseEntity<>(supermarketService.addItemsToSupermarket(addItemsParams), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Supermarket> getSupermarketbyId(@PathVariable(value = "id", required = true) String id) {
        return new ResponseEntity(supermarketService.getSupermarketById(id), HttpStatus.OK);
    }
}
