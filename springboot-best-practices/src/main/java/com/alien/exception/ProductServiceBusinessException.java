package com.alien.exception;

public class ProductServiceBusinessException extends RuntimeException{

    public ProductServiceBusinessException(String message) {
        super(message);
    }
}
