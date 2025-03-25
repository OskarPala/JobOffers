package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.OfferResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class OfferFacadeTest {
    @Test
    public void should_fetch_jobs_from_remote_and_save_all_offers_when_repository_is_empty(){
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();
        assertThat(offerFacade.findAllOffers().isEmpty());
        //when
        List<OfferResponseDto> result= offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(result).hasSize(6);

    }
    @Test
    public void should_save_4_offers_when_there_are_no_offers_in_database() {

    }
    @Test
    public void should_save_only_2_offers_when_repository_had_4_added_with_offer_urls() {

    }
    @Test
    public void should_throw_duplicate_key_exception_when_with_offer_url_exist() {

    }
    @Test
    public void should_throw_not_found_exception_when_offer_not_found() {

    }
    @Test
    public void should_fetch_from_jobs_from_remote_and_save_all_offers_when_repository_is_empty() {

    }
    @Test
    public void should_find_offer_by_id_when_offer_was_saved() {

    }

}

