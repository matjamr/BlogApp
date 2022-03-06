package com.example.testingproject.service.exceptions.UserExceptions;

public class UserWithGivenEmailNotExist extends Exception{
    public UserWithGivenEmailNotExist(String message) {
        super(message);
    }
}
