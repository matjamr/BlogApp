package com.example.testingproject.controller.request.UserRequest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String description;
    @NotNull
    private String email;
}
