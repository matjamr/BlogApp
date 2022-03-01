package com.example.testingproject.controller.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindUserResponse {
    private String name;
    private String surname;
    private String description;
    private String email;
    private LocalDateTime date;
}
