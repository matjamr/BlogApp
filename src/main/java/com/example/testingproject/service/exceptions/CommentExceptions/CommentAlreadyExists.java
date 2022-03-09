package com.example.testingproject.service.exceptions.CommentExceptions;

public class CommentAlreadyExists extends Exception{
    public CommentAlreadyExists(String message) {
        super(message);
    }
}
