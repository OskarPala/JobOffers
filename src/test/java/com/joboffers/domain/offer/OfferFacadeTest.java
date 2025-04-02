package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponse;
import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class OfferFacadeTest {
    @Test
    public void should_fetch_jobs_from_remote_and_save_all_offers_when_repository_is_empty() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();
        assertThat(offerFacade.findAllOffers().isEmpty());
        //when
        List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(result).hasSize(6);

    }

    @Test
    public void should_save_3_offers_when_database_is_empty() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();
        assertThat(offerFacade.findAllOffers()).isEmpty();
        //when
        offerFacade.saveOffer(new OfferRequestDto("asd", "qwe", "123", "asd1"));
        offerFacade.saveOffer(new OfferRequestDto("asd", "qwe", "123", "asd2"));
        offerFacade.saveOffer(new OfferRequestDto("asd", "qwe", "123", "asd3"));
        List<OfferResponseDto> result = offerFacade.findAllOffers();
        //then
        assertThat(result).hasSize(3);

    }

    @Test
    public void should_save_only_2_offers_when_repository_had_3_added_with_unique_offer_urls() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(
                List.of(
                        new JobOfferResponse("asd", "qwe", "123", "asd1"),
                        new JobOfferResponse("asd", "qwe", "123", "asd2"),
                        new JobOfferResponse("asd", "qwe", "123", "asd3"),
                        new JobOfferResponse("Junior", "company 1", "123", "https://jobboard.pl/1"),
                        new JobOfferResponse("Java", "company 2", "123", "https://jobboard.pl/2")
                )
        ).offerFacadeForTests();
        offerFacade.saveOffer(new OfferRequestDto("asd", "qwe", "123", "asd1"));
        offerFacade.saveOffer(new OfferRequestDto("asd", "qwe", "123", "asd2"));
        offerFacade.saveOffer(new OfferRequestDto("asd", "qwe", "123", "asd3"));
        assertThat(offerFacade.findAllOffers()).hasSize(3);
        //when
        List<OfferResponseDto> response = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(List.of(
                        response.get(0).offerUrl(),
                        response.get(1).offerUrl()
                )
        ).containsExactlyInAnyOrder("https://jobboard.pl/1", "https://jobboard.pl/2");
    }

    @Test
    public void should_find_offer_by_id_when_offer_was_saved() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("id", "junior", "1000", "job.com/1"));
        //when
        OfferResponseDto responseDto = offerFacade.findOfferById(offerResponseDto.id());
        //then
        assertThat(responseDto).isEqualTo(OfferResponseDto.builder()
                .id(offerResponseDto.id())
                .companyName("id")
                .position("junior")
                .salary("1000")
                .offerUrl("job.com/1")
                .build()
        );
    }

    @Test
    public void should_throw_not_found_exception_when_offer_not_found() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        assertThat(offerFacade.findAllOffers()).isEmpty();
        //when
        Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("100"));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id 100 not found");
    }

    @Test
    public void should_throw_duplicate_key_exception_when_offer_with_this_url_exist() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("id", "asd", "9999", "job.com/1"));
        String saveId = offerResponseDto.id();
        assertThat(offerFacade.findOfferById(saveId).id()).isEqualTo(saveId);
        //when
        Throwable thrown = catchThrowable(() -> offerFacade.saveOffer(
                new OfferRequestDto("id", "asd", "9999", "job.com/1")));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferDuplicateException.class)
                .hasMessage("Offer with offerUrl [job.com/1] already exists");

    }
}

