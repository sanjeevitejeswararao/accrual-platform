package com.example.dealservice.service;

import com.example.dealservice.entity.IdempotencyRecord;
import com.example.dealservice.repository.IdempotencyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdempotencyService {

    private final IdempotencyRepository repository;

    // Injects your existing IdempotencyRepository into the service layer
    public IdempotencyService(IdempotencyRepository repository) {
        this.repository = repository;
    }

    public Optional<IdempotencyRecord> find(String key) {
        return repository.findByIdempotencyKey(key);
    }
}
