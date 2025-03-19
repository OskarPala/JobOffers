package com.joboffers.domain.offer;

import java.util.List;

interface OfferRepository {

    List<Offer> findAll();
}
