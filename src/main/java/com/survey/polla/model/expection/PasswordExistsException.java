package com.survey.polla.model.expection;

public class PasswordExistsException extends Exception {
    public PasswordExistsException(String message) {
        super(message);
    }
}
