package com.joboffers.domain.offer;

import java.util.List;

public interface OfferRepository {
    boolean existsByOfferUrl(String offerUrl);

    List<Offer> findAll();

    Offer save(Offer offer);

    List<Offer> saveAll(List<Offer> offers);
}
