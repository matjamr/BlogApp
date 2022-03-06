package com.example.testingproject.service.exceptions.UserExceptions;

public class UserNotExistException extends Exception {
    public UserNotExistException(String message) {
        super(message);
    }
}
