package com.survey.polla.model.expection;

public class PasswordLengthException extends Exception {
    public PasswordLengthException(String message) {
        super(message);
    }
}
