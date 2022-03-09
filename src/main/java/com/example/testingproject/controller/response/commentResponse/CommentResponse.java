package com.example.testingproject.controller.response.commentResponse;

import com.example.testingproject.controller.response.UserResponse.UserResponse;
import com.example.testingproject.entity.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Integer id;
    private String content;
    private UserResponse user;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.user = new UserResponse(comment.getUser());
    }
}
