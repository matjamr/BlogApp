package com.example.testingproject.controller.response;

import com.example.testingproject.entity.Post;
import lombok.Data;

@Data
public class PostResponse {
    private String title;
    private String content;

    public PostResponse() {}

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
