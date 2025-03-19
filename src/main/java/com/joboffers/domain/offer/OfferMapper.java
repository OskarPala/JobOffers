package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.OfferResponseDto;

class OfferMapper {
    public static OfferResponseDto mapFromOfferToOfferDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.id())
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }
}
