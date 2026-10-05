package com.example.dealservice.dto;

import com.example.dealservice.entity.DealStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record DealResponse(
        Long id,
        String externalReference,
        BigDecimal principalAmount,
        String currency,
        BigDecimal annualRate,
        LocalDate startDate,
        LocalDate maturityDate,
        Integer dayCountBasis,
        DealStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}