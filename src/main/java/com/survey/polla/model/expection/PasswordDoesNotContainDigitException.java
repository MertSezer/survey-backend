package com.survey.polla.model.expection;

public class PasswordDoesNotContainDigitException extends Exception {
    public PasswordDoesNotContainDigitException(String message) {
        super(message);
    }
}
