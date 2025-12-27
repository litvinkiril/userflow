package com.hse.userflow.storingservice.handler;

import com.hse.userflow.dto.error.ErrorResponse;
import com.hse.userflow.storingservice.exception.FileDownloadException;
import com.hse.userflow.storingservice.exception.FileUploadException;
import com.hse.userflow.storingservice.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FileDownloadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleFileDownload(final FileDownloadException exception) {
        log.error("file download exception", exception);
        ErrorResponse errorResponse = new ErrorResponse("file download", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleFileUpload(FileUploadException exception) {
        log.error("file upload exception", exception);
        ErrorResponse errorResponse = new ErrorResponse("file upload", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFound(final NotFoundException exception) {
        log.error("not found", exception);
        ErrorResponse errorResponse = new ErrorResponse("not found", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        log.error("exception", exception);
        ErrorResponse errorResponse = new ErrorResponse("exception", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
