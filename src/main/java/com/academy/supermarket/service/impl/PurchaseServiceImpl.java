package com.academy.supermarket.service.impl;

import com.academy.supermarket.exception.SupermarketNotFoundException;
import com.academy.supermarket.model.entity.Item;
import com.academy.supermarket.model.entity.Purchase;
import com.academy.supermarket.model.entity.Supermarket;
import com.academy.supermarket.model.enums.PaymentType;
import com.academy.supermarket.model.util.PurchaseParams;
import com.academy.supermarket.repository.ItemRepository;
import com.academy.supermarket.repository.PurchaseRepository;
import com.academy.supermarket.repository.SupermarketRepository;
import com.academy.supermarket.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private SupermarketRepository supermarketRepository;
    private ItemRepository itemRepository;
    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(SupermarketRepository supermarketRepository, ItemRepository itemRepository, PurchaseRepository purchaseRepository) {
        this.supermarketRepository = supermarketRepository;
        this.itemRepository = itemRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase createPurchase(PurchaseParams purchaseParams) {

        Purchase purchase = new Purchase();

        Supermarket supermarket = supermarketRepository.findById(purchaseParams.getSuperMarketId())
                .orElseThrow(() -> new SupermarketNotFoundException(MessageFormat.format("Supermarket with id:{0} not found", purchaseParams.getSuperMarketId())));

        List<Item> supermarketList = supermarket.getItemList();
        List<String> purchaseList = purchaseParams.getItemIds();
        List<Item> available = new ArrayList<>();
        for (String itemId : purchaseList) {
            Optional<Item> item = itemRepository.findById(itemId);
            item.ifPresent(available::add);
        }
        supermarketList.retainAll(available);
        purchase.setTotalPrice(supermarketList.stream().mapToDouble(Item::getPrice).sum());

        if (purchaseParams.getPaymentType().equalsIgnoreCase(PaymentType.CASH.toString())
                && (purchaseParams.getCashAmount() >= purchase.getTotalPrice())) {
            purchase.setPaymentType(PaymentType.CASH);
            purchase.setCashAmount(purchaseParams.getCashAmount());
            purchase.setExchange(purchaseParams.getCashAmount() - purchase.getTotalPrice());

        } else if (purchaseParams.getPaymentType().equalsIgnoreCase(PaymentType.CARD.toString())) {
            purchase.setPaymentType(PaymentType.CARD);
        }

        purchase.setPaymentExecutedTime(LocalDateTime.now());
        purchase.setSuperMarketId(purchaseParams.getSuperMarketId());
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }
}
