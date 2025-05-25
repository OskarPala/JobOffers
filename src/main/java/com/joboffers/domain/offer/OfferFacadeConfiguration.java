package com.joboffers.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
@Configuration
public class OfferFacadeConfiguration {
    @Bean
    OfferFacade offerFacade(OfferFetchable offerFetchable){
        OfferRepository repo = new OfferRepository() {
            @Override
            public boolean existsByOfferUrl(final String offerUrl) {
                return false;
            }
            @Override
            public Optional<Offer> findByOfferUrl(String offerUrl) {
                return Optional.empty();
            }

            @Override
            public List<Offer> findAll() {
                return null;
            }

            @Override
            public Offer save(final Offer offer) {
                return null;
            }

            @Override
            public List<Offer> saveAll(final List<Offer> offers) {
                return null;
            }

            @Override
            public Optional<Offer> findById(final String id) {
                return Optional.empty();
            }
        };
        OfferService offerService = new OfferService(repo,offerFetchable);
        return new OfferFacade(repo,offerService);
    }
}
