package com.example.testingproject.converter;

import com.example.testingproject.controller.request.UserRequest.SaveUserRequest;
import com.example.testingproject.controller.request.UserRequest.UpdateUserRequest;
import com.example.testingproject.controller.response.UserResponse.FindUserResponse;
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

    public User toUser(final UpdateUserRequest user) {
        final User newUser = new User();
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setDescription(newUser.getDescription());

        return newUser;
    }

    public User toUser(FindUserResponse userResponse) {
        final User user = new User();
        user.setName(userResponse.getName());
        user.setSurname(userResponse.getSurname());
        user.setEmail(userResponse.getEmail());
        user.setDescription(userResponse.getDescription());
        user.setDateOfAccountCreation(userResponse.getDate());
        return user;
    }

    public User toUser(UpdateUserRequest userResponse, User user) {
        user.setName(userResponse.getName());
        user.setSurname(userResponse.getSurname());
        user.setDescription(userResponse.getDescription());
        return user;
    }
}
