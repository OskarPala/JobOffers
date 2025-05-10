package com.joboffers.infrastructure.offer.http;

import com.joboffers.domain.offer.OfferFetchable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
class OfferHttpClientConfig {
    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(@Value("1000") int connectionTimeout,
                                     @Value("1000") int readTimeout,
                                     RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(restTemplateResponseErrorHandler);
        return restTemplate;
    }

    @Bean
    public OfferFetchable remoteOfferClient(RestTemplate restTemplate,
                                            @Value("${offer.http.client.config.uri:http://example.com}") String uri,
                                            @Value("5057") int port) {
        return new OfferHttpClient(restTemplate, uri, port);
    }
}
