package com.example.dealservice.exception;

public class InvalidDealStateException extends RuntimeException {

    public InvalidDealStateException(String message) {
        super(message);
    }
}