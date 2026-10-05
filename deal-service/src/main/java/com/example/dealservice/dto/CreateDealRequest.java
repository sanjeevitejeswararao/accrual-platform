package com.example.dealservice.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateDealRequest(

        @NotBlank(message = "External reference is required")
        @Size(max = 64)
        String externalReference,

        @NotNull(message = "Principal amount is required")
        @DecimalMin(value = "0.0001")
        @Digits(integer = 15, fraction = 4)
        BigDecimal principalAmount,

        @NotBlank
        @Pattern(regexp = "^[A-Z]{3}$",
                message = "Currency must be a 3-letter uppercase code")
        String currency,

        @NotNull
        @DecimalMin(value = "0.00000001")
        @DecimalMax(value = "1.00000000")
        @Digits(integer = 4, fraction = 8)
        BigDecimal annualRate,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate maturityDate,

        @NotNull
        @Min(1)
        @Max(366)
        Integer dayCountBasis

) {
    @AssertTrue(message = "Maturity date must be after start date")
    public boolean isDateRangeValid() {
        if (startDate == null || maturityDate == null) {
            return true;
        }

        return maturityDate.isAfter(startDate);
    }
}