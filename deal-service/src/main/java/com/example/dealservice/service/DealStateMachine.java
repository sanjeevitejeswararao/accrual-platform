package com.example.dealservice.service;

import com.example.dealservice.entity.DealStatus;
import com.example.dealservice.exception.InvalidDealStateException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class DealStateMachine {

    private static final Map<DealStatus, Set<DealStatus>>
            ALLOWED_TRANSITIONS = Map.of(

            DealStatus.RECEIVED,
            Set.of(
                    DealStatus.VALIDATED,
                    DealStatus.CANCELLED
            ),

            DealStatus.VALIDATED,
            Set.of(
                    DealStatus.PROCESSING,
                    DealStatus.CANCELLED
            ),

            DealStatus.PROCESSING,
            Set.of(
                    DealStatus.PROCESSED,
                    DealStatus.FAILED
            ),

            DealStatus.FAILED,
            Set.of(
                    DealStatus.PROCESSING
            )
    );

    public void validateTransition(
            DealStatus current,
            DealStatus target) {

        Set<DealStatus> allowed =
                ALLOWED_TRANSITIONS.getOrDefault(
                        current,
                        Set.of()
                );

        if (!allowed.contains(target)) {
            throw new InvalidDealStateException(
                    "Invalid transition from "
                            + current
                            + " to "
                            + target
            );
        }
    }
}