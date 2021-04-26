package com.academy.supermarket.service.impl;

import com.academy.supermarket.exception.SupermarketAlreadyExists;
import com.academy.supermarket.exception.SupermarketNotFoundException;
import com.academy.supermarket.model.entity.Item;
import com.academy.supermarket.model.entity.Supermarket;
import com.academy.supermarket.model.util.AddItemsParams;
import com.academy.supermarket.repository.ItemRepository;
import com.academy.supermarket.repository.SupermarketRepository;
import com.academy.supermarket.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupermarketServiceImpl implements SupermarketService {
    private SupermarketRepository supermarketRepository;
    private ItemRepository itemRepository;

    @Autowired
    public SupermarketServiceImpl(SupermarketRepository supermarketRepository, ItemRepository itemRepository) {
        this.supermarketRepository = supermarketRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Supermarket createSupermarket(Supermarket supermarket) {
        Supermarket supermarketWithName = new Supermarket();
        supermarketWithName.setName(supermarket.getName());
        Example<Supermarket> exampleSupermarket = Example.of(supermarketWithName);
        Optional<Supermarket> optionalSupermarket = supermarketRepository.findOne(exampleSupermarket);
        if(optionalSupermarket.isPresent()){
            throw new SupermarketAlreadyExists("Supermarket with this name already exists!");
        }
        return supermarketRepository.save(supermarket);
    }

    @Override
    public Supermarket addItemsToSupermarket(AddItemsParams addItemsParams) {
        Supermarket supermarket = supermarketRepository.findById(addItemsParams.getId()).orElseThrow(() -> new SupermarketNotFoundException(MessageFormat.format("Supermarket with UUID:{0} not found.", addItemsParams.getId())));

        List itemsToBeAdded = addItemsParams.getItemIds();
        List<Item> addedItems = new ArrayList<>();
        if (supermarket.getItemList().isEmpty()) {
            for (Object id : itemsToBeAdded) {
                Optional<Item> itemWithId = itemRepository.findById(id.toString());
                itemWithId.ifPresent(addedItems::add);
            }
            supermarket.setItemList(addedItems);
        } else {
            List<Item> supermarketList = supermarket.getItemList();
            for (Object id : itemsToBeAdded) {
                Optional<Item> itemWithId = itemRepository.findById(id.toString());
                itemWithId.ifPresent(supermarketList::add);
                supermarket.setItemList(supermarketList);
            }
        }

        return supermarketRepository.save(supermarket);
    }

    public Optional<Supermarket> getSupermarketById(String id) {
        return supermarketRepository.findById(id);
    }

}
