package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferFacade {
    private final OfferRepository offerRepository;
    private final OfferService offerService;

    public List<OfferResponseDto> findAllOffers(){
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .collect(Collectors.toList());
    }
    public List<OfferResponseDto>fetchAllOffersAndSaveAllIfNotExists (){
        return offerService.fetchAllOffersAndSaveAllIfNotExists()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .toList();
    }
    public OfferResponseDto saveOffer (OfferRequestDto requestDto){
        final Offer offerToSave = OfferMapper.mapFromOfferDtoToOffer(requestDto);
        final Offer save = offerRepository.save(offerToSave);
        return OfferMapper.mapFromOfferToOfferDto(save);
    }
    public OfferResponseDto findOfferById (String id){
        return offerRepository.findById(id)
                .map(OfferMapper::mapFromOfferToOfferDto)
                .orElseThrow(()->new OfferNotFoundException(id));
    }
}