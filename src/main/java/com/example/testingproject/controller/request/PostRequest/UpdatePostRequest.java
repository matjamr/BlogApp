package com.example.testingproject.controller.request.PostRequest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdatePostRequest {
    @NotNull
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private String content;
}
