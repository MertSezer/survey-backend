package com.survey.polla.model.exception;

public class PasswordExistsException extends Exception {
    public PasswordExistsException(String message) {
        super(message);
    }
}
