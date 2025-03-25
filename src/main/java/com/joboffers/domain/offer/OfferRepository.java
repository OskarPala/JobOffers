package com.joboffers.domain.offer;

import java.util.List;

interface OfferRepository {
    boolean existsByOfferUrl(String offerUrl);
    List<Offer> findAll();

    List<Offer> saveAll(List<Offer> offers);
}
