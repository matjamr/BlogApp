package com.example.testingproject.converter;

import com.example.testingproject.controller.request.SaveUserRequest;
import com.example.testingproject.controller.response.FindUserResponse;
import com.example.testingproject.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toUser(final SaveUserRequest saveUserRequest) {
        final User user = new User();
        user.setName(saveUserRequest.getName());
        user.setSurname(saveUserRequest.getSurname());
        user.setDescription(saveUserRequest.getDescription());
        user.setEmail(saveUserRequest.getEmail());
        return user;
    }

    public FindUserResponse toDto(final User user) {
        final FindUserResponse response = new FindUserResponse();
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setEmail(user.getEmail());
        response.setDescription(user.getDescription());
        response.setDate(user.getDateOfAccountCreation());
        return response;
    }
}
