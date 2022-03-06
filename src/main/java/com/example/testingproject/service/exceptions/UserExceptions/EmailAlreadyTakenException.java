package com.example.testingproject.service.exceptions.UserExceptions;

public class EmailAlreadyTakenException extends Exception{

    public EmailAlreadyTakenException(String message) {
        super(message);
    }
}
