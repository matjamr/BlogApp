package com.example.testingproject.controller.response;

import com.example.testingproject.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FindUserResponse {
    private String name;
    private String surname;
    private String description;
    private String email;
    private LocalDateTime date;
    private List<PostResponse> posts;

    public FindUserResponse() {}

    public FindUserResponse(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.description = user.getDescription();
        this.email = user.getEmail();
        this.date = user.getDateOfAccountCreation();
        posts = user.getPosts().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

}
