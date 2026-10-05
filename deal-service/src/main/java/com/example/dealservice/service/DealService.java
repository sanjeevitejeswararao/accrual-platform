package com.example.dealservice.service;

import com.example.dealservice.dto.*;
import com.example.dealservice.entity.*;
import com.example.dealservice.exception.*;
import com.example.dealservice.mapper.DealMapper;
import com.example.dealservice.repository.DealRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DealService {

    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    public DealService(
            DealRepository dealRepository,
            DealMapper dealMapper) {

        this.dealRepository = dealRepository;
        this.dealMapper = dealMapper;
    }

    @Transactional
    public DealResponse createDeal(CreateDealRequest request) {

        if (dealRepository.existsByExternalReference(
                request.externalReference())) {

            throw new InvalidDealStateException(
                    "Deal reference already exists"
            );
        }

        Deal deal = dealMapper.toEntity(request);

        Deal savedDeal = dealRepository.save(deal);

        return dealMapper.toResponse(savedDeal);
    }

    @Transactional(readOnly = true)
    public DealResponse getDeal(Long id) {

        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new DealNotFoundException(id));

        return dealMapper.toResponse(deal);
    }

    @Transactional(readOnly = true)
    public Page<DealResponse> getAllDeals(Pageable pageable) {

        return dealRepository.findAll(pageable)
                .map(dealMapper::toResponse);
    }

    @Transactional
    public DealResponse updateDeal(
            Long id,
            UpdateDealRequest request) {

        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new DealNotFoundException(id));

        if (deal.getStatus() != DealStatus.RECEIVED) {
            throw new InvalidDealStateException(
                    "Only RECEIVED deals can be updated"
            );
        }

        deal.setPrincipalAmount(request.principalAmount());

        deal.setAnnualRate(request.annualRate());

        deal.setStartDate(request.startDate());

        deal.setMaturityDate(request.maturityDate());

        Deal updatedDeal = dealRepository.save(deal);

        return dealMapper.toResponse(updatedDeal);
    }
}