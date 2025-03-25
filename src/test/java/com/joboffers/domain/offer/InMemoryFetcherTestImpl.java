package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponse;

import java.util.List;

public class InMemoryFetcherTestImpl implements OfferFetchable{
    List<JobOfferResponse> listOffers;

    InMemoryFetcherTestImpl( List<JobOfferResponse> listOffers) {
        this.listOffers = listOffers;
    }
    @Override
    public List<JobOfferResponse> fetchOffers(){return listOffers;}
}
