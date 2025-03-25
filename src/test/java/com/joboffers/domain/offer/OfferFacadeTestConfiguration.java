package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponse;

import java.util.List;

public class OfferFacadeTestConfiguration {
    private final InMemoryFetcherTestImpl inMemoryFetcherTest;
    private final InMemoryOfferRepository offerRepository;

    OfferFacadeTestConfiguration() {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(
                List.of(
                        new JobOfferResponse("id","id","asdf","1"),
                        new JobOfferResponse("qwe","id","asdf","2"),
                        new JobOfferResponse("asd","id","asdf","3"),
                        new JobOfferResponse("zxc","id","asdf","4"),
                        new JobOfferResponse("rty","id","asdf","5"),
                        new JobOfferResponse("fgh","id","asdf","6")
                )
        );
        this.offerRepository = new InMemoryOfferRepository();
    }
    OfferFacadeTestConfiguration(List<JobOfferResponse> remoteClientOffers){
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(remoteClientOffers);
        this.offerRepository = new InMemoryOfferRepository();
    }
    OfferFacade offerFacadeForTests(){
        return new OfferFacade(offerRepository, new OfferService(offerRepository, inMemoryFetcherTest));
    }
}
