package com.academy.supermarket.exception;

public class SupermarketAlreadyExists extends RuntimeException {
    public SupermarketAlreadyExists(String message) {
        super(message);
    }
}
