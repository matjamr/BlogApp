package com.example.testingproject.controller.response.PostResponse;

import com.example.testingproject.entity.User;
import lombok.Data;

@Data
public class UpdateUserResponse {
    private String name;
    private String surname;
    private String description;

    public UpdateUserResponse(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.description = user.getDescription();
    }
}
