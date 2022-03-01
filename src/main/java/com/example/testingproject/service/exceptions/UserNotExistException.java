package com.example.testingproject.service.exceptions;

public class UserNotExistException extends Exception {
    public UserNotExistException(String message) {
        super(message);
    }
}
