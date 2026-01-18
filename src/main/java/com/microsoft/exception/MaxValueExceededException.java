package com.microsoft.exception;

public class MaxValueExceededException extends RuntimeException {
    public MaxValueExceededException(String message) {
        super(message);
    }
}
