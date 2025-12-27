package com.hse.userflow.gateway.client;

import com.hse.userflow.dto.error.ErrorResponse;
import com.hse.userflow.dto.user.UserCreateDto;
import com.hse.userflow.dto.user.UserDto;
import com.hse.userflow.gateway.exception.GateWayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.hse.userflow.gateway.utils.ErrorParser.toErrorResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserStorageClient {
    private final WebClient fileStorageWebClient;
    private final String USERS_URI = "/api/users";

    public UserDto createUser(UserCreateDto newUser) {
        try {
            return fileStorageWebClient
                    .post()
                    .uri(USERS_URI)
                    .bodyValue(newUser)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public UserDto findUserById(Integer userId) {
        try {
            return fileStorageWebClient
                    .get()
                    .uri(USERS_URI + "/{userId}", userId)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            ErrorResponse errorResponse = toErrorResponse(e);
            throw new GateWayException(HttpStatus.valueOf(e.getStatusCode().value()), errorResponse);
        }
    }

    public void deleteUserById(Integer userId) {
        try {
            fileStorageWebClient
                    .delete()
                    .uri(USERS_URI + "/{userId}", userId)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

        } catch (WebClientResponseException exception) {
            ErrorResponse errorResponse = toErrorResponse(exception);
            throw new GateWayException(HttpStatus.valueOf(exception.getStatusCode().value()), errorResponse);
        }
    }


}
