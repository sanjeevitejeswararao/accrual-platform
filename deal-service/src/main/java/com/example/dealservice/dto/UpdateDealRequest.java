package com.example.dealservice.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateDealRequest(

        @NotNull
        @DecimalMin("0.0001")
        @Digits(integer = 15, fraction = 4)
        BigDecimal principalAmount,

        @NotNull
        @DecimalMin("0.00000001")
        @DecimalMax("1.00000000")
        @Digits(integer = 4, fraction = 8)
        BigDecimal annualRate,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate maturityDate

) {
    @AssertTrue(message = "Maturity date must be after start date")
    public boolean isDateRangeValid() {
        if (startDate == null || maturityDate == null) {
            return true;
        }

        return maturityDate.isAfter(startDate);
    }
}