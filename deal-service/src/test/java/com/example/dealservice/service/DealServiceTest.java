package com.example.dealservice.service;

import com.example.dealservice.dto.CreateDealRequest;
import com.example.dealservice.dto.DealResponse;
import com.example.dealservice.entity.Deal;
import com.example.dealservice.entity.DealStatus;
import com.example.dealservice.exception.InvalidDealStateException;
import com.example.dealservice.mapper.DealMapper;
import com.example.dealservice.repository.DealRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DealServiceTest {

    @Mock
    private DealRepository dealRepository;

    @Mock
    private DealMapper dealMapper;

    @InjectMocks
    private DealService dealService;

    @Test
    void shouldRejectDuplicateDealReference() {

        CreateDealRequest request = new CreateDealRequest(
                "SIM-DEAL-10001",
                new BigDecimal("1000000.0000"),
                "USD",
                new BigDecimal("0.06000000"),
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2027, 10, 1),
                360
        );

        when(dealRepository.existsByExternalReference(
                "SIM-DEAL-10001"
        )).thenReturn(true);

        assertThrows(
                InvalidDealStateException.class,
                () -> dealService.createDeal(request)
        );

        verify(dealRepository, never()).save(any(Deal.class));
    }
}