package com.joboffers.controller.error;

import com.joboffers.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OfferUrlDuplicateErrorIntegrationTest extends BaseIntegrationTest {
    @Test
    public void should_return_409_conflict_when_added_second_offer_with_same_offer_url() throws Exception {
//step1
//given & when
        ResultActions perform1 = mockMvc.perform(post("/offers")
                .content("""
                        {
                        "companyName": "company",
                        "position": "position",
                        "salary": "5 000 - 8 000 PLN",
                        "offerUrl": "https://offers.pl/offer/1234"
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"));
//then
        perform1.andExpect(status().isCreated());
//step2
//given && when
        ResultActions perform2 = mockMvc.perform(post("/offers")
                .content("""
                        {
                        "companyName": "company",
                        "position": "position",
                        "salary": "5 000 - 8 000 PLN",
                        "offerUrl": "https://offers.pl/offer/1234"
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"));
//then
        perform2.andExpect(status().isConflict());
    }
}
