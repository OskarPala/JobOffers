package com.joboffers.infrastructure.offer.http;

import com.joboffers.domain.offer.OfferFetchable;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@AllArgsConstructor
@Configuration
public class OfferHttpClientConfig {

    private final OfferHttpClientConfigProperties properties;

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(OfferHttpClientConfigProperties properties,
                                     RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(properties.connectionTimeout()))
                .setReadTimeout(Duration.ofMillis(properties.readTimeout()))
                .build();
    }

    @Bean
    public OfferFetchable remoteOfferClient(OfferHttpClientConfigProperties properties, RestTemplate restTemplate
    ) {
        return new OfferHttpClient(restTemplate, properties.uri(), properties.port());
    }
}
