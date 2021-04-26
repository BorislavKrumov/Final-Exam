package com.academy.supermarket.exception;

public class SupermarketNotFoundException extends RuntimeException {
    public SupermarketNotFoundException(String message){
        super(message);
    }
}
