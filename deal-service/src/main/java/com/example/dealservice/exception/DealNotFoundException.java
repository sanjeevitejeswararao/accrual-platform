package com.example.dealservice.exception;

public class DealNotFoundException extends RuntimeException {

    public DealNotFoundException(Long id) {
        super("Deal not found with ID: " + id);
    }
}