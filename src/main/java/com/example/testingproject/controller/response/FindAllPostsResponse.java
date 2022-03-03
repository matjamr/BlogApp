package com.example.testingproject.controller.response;

import com.example.testingproject.entity.Post;
import lombok.Data;

@Data
public class FindAllPostsResponse {

    String title;
    String content;
    UserResponse user;



    public FindAllPostsResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        user = new UserResponse(post.getUser());
    }
}
