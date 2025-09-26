package com.joboffers.controller.error;

import com.joboffers.BaseIntegrationTest;
import com.joboffers.infrastructure.offer.controller.error.OfferPostErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OfferUrlDuplicateErrorIntegrationTest extends BaseIntegrationTest {
    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.4"));

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void should_return_409_conflict_when_added_second_offer_with_same_offer_url() throws Exception {
//step1: User make POST request with valid JSON format and system return status 201 with offer
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
//step2: User make POST request with valid JSON format,but offerUrl is duplicated and system return 409 with error message
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
        String errorResponse = perform2.andExpect(status().isConflict())
                .andReturn()
                .getResponse()
                .getContentAsString();
        OfferPostErrorResponse parsedResponseJson = objectMapper.readValue(errorResponse, OfferPostErrorResponse.class);
//then
        assertThat(parsedResponseJson.messages())
                .contains("Offer url already exists");
    }
}
