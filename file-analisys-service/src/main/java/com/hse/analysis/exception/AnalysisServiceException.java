package com.hse.analysis.exception;

public class AnalysisServiceException extends RuntimeException {
    public AnalysisServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalysisServiceException(String message) {
        super(message);
    }
}
