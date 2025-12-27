package com.hse.userflow.gateway.exception;

import com.hse.userflow.dto.error.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@ToString
@Getter
public class GateWayException extends RuntimeException {
    private final HttpStatus status;
    private final ErrorResponse errorResponse;

}
