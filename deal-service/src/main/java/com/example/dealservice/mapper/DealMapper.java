package com.example.dealservice.mapper;

import com.example.dealservice.dto.*;
import com.example.dealservice.entity.Deal;
import com.example.dealservice.entity.DealStatus;
import org.springframework.stereotype.Component;

@Component
public class DealMapper {

    public Deal toEntity(CreateDealRequest request) {

        Deal deal = new Deal();

        deal.setExternalReference(
                request.externalReference()
        );

        deal.setPrincipalAmount(
                request.principalAmount()
        );

        deal.setCurrency(request.currency());

        deal.setAnnualRate(request.annualRate());

        deal.setStartDate(request.startDate());

        deal.setMaturityDate(request.maturityDate());

        deal.setDayCountBasis(request.dayCountBasis());

        deal.setStatus(DealStatus.RECEIVED);

        return deal;
    }

    public DealResponse toResponse(Deal deal) {

        return new DealResponse(
                deal.getId(),
                deal.getExternalReference(),
                deal.getPrincipalAmount(),
                deal.getCurrency(),
                deal.getAnnualRate(),
                deal.getStartDate(),
                deal.getMaturityDate(),
                deal.getDayCountBasis(),
                deal.getStatus(),
                deal.getCreatedAt(),
                deal.getUpdatedAt()
        );
    }
}