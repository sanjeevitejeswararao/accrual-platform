package com.example.dealservice.repository;

import com.example.dealservice.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealRepository
        extends JpaRepository<Deal, Long> {

    boolean existsByExternalReference(String externalReference);

    Optional<Deal> findByExternalReference(String externalReference);
}