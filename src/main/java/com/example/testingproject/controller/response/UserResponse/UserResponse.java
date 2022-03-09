package com.example.testingproject.controller.response.UserResponse;

import com.example.testingproject.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String name;
    private String surname;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
    }
}
