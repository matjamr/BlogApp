package com.example.testingproject.service.exceptions.PostExceptions;

public class PostDoesNotExistsError extends Exception{
    public PostDoesNotExistsError(String message) {
        super(message);
    }
}
