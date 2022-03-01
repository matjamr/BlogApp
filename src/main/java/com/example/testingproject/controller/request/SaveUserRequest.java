package com.example.testingproject.controller.request;

import lombok.Data;

@Data
public class SaveUserRequest {
    private String name;
    private String surname;
    private String description;
    private String email;
}
