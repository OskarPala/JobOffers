package com.joboffers.infrastructure.offer.http;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties(prefix = "offer.http.client.config")
public record OfferHttpClientConfigProperties(long connectionTimeout,
                                              long readTimeout,
                                              String uri,
                                              int port) {
}
