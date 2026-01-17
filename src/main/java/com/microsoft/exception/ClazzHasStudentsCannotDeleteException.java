package com.microsoft.exception;

public class ClazzHasStudentsCannotDeleteException extends RuntimeException {
    public ClazzHasStudentsCannotDeleteException(String message) {
        super(message);
    }
}
