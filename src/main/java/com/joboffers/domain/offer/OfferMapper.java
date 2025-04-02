package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponse;
import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;

public class OfferMapper {
    public static OfferResponseDto mapFromOfferToOfferDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.id())
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }

    public static Offer mapFromOfferResponseToOffer(JobOfferResponse jobOffer) {
        return Offer.builder()
                .offerUrl(jobOffer.offerUrl())
                .salary(jobOffer.salary())
                .position(jobOffer.title())
                .companyName(jobOffer.company())
                .build();
    }
    public static Offer mapFromOfferDtoToOffer(OfferRequestDto offerDto){
        return Offer.builder()
                .companyName(offerDto.companyName())
                .position(offerDto.position())
                .salary(offerDto.salary())
                .offerUrl(offerDto.offerUrl())
                .build();
    }
}
