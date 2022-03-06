package com.example.testingproject.controller.response.UserResponse;

import com.example.testingproject.entity.Post;
import lombok.Data;

@Data
public class PostResponse {
    private Integer id;
    private String title;
    private String content;

    public PostResponse() {}

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
