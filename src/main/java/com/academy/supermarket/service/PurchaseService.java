package com.academy.supermarket.service;

import com.academy.supermarket.model.entity.Purchase;
import com.academy.supermarket.model.util.PurchaseParams;

import java.util.List;

public interface PurchaseService {
    Purchase createPurchase(PurchaseParams purchaseParams);
    List<Purchase> getAll();
}
