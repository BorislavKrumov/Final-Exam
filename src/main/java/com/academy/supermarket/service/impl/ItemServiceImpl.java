package com.academy.supermarket.service.impl;

import com.academy.supermarket.model.entity.Item;
import com.academy.supermarket.repository.ItemRepository;
import com.academy.supermarket.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
}
