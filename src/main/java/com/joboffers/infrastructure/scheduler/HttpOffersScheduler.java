package com.joboffers.infrastructure.scheduler;

import com.joboffers.domain.offer.OfferFacade;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class HttpOffersScheduler {
    private final OfferFacade offerFacade;
}
