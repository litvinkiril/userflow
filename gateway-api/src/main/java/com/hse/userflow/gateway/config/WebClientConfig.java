package com.hse.userflow.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${storing-service.url}")
    private String storingServiceUrl;

    @Value("${analysis-service.url}")
    private String analysisServiceUrl;

    @Bean
    public WebClient fileStorageWebClient() {
        return WebClient.builder()
                .baseUrl(storingServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient analysisServiceWebClient() {
        return WebClient.builder()
                .baseUrl(analysisServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


}
