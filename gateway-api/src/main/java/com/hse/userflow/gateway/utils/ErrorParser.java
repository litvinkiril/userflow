package com.hse.userflow.gateway.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hse.userflow.dto.error.ErrorResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ErrorParser {
    public static ErrorResponse toErrorResponse(WebClientResponseException exception) {
        try {
            return new ObjectMapper().readValue(exception.getResponseBodyAsString(), ErrorResponse.class);
        } catch (JsonProcessingException ex) {
            return new ErrorResponse("error", exception.getResponseBodyAsString());
        }
    }
}
