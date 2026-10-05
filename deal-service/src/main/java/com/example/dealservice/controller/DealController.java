package com.example.dealservice.controller;

import com.example.dealservice.dto.*;
import com.example.dealservice.service.DealService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping
    public ResponseEntity<DealResponse> createDeal(
            @Valid @RequestBody CreateDealRequest request) {

        DealResponse response = dealService.createDeal(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealResponse> getDeal(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                dealService.getDeal(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<DealResponse>> getAllDeals(
            @PageableDefault(size = 20)
            Pageable pageable) {

        return ResponseEntity.ok(
                dealService.getAllDeals(pageable)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealResponse> updateDeal(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDealRequest request) {

        return ResponseEntity.ok(
                dealService.updateDeal(id, request)
        );
    }
}