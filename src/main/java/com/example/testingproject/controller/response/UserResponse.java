package com.example.testingproject.controller.response;

import com.example.testingproject.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    private String name;
    private String surname;

    public UserResponse(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
    }
}
