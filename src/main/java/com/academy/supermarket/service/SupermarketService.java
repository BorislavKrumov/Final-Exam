package com.academy.supermarket.service;

import com.academy.supermarket.model.entity.Supermarket;
import com.academy.supermarket.model.util.AddItemsParams;

import java.util.Optional;

public interface SupermarketService {
    Supermarket createSupermarket(Supermarket supermarket);
    Supermarket addItemsToSupermarket(AddItemsParams addItemsParams);
    Optional<Supermarket> getSupermarketById(String id);
}
