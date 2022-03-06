package com.example.testingproject.controller.request.PostRequest;

import com.example.testingproject.entity.User;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class SavePostRequest {
    String title;
    String content;
    User user;
}
