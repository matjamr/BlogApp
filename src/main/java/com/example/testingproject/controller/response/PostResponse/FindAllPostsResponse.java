package com.example.testingproject.controller.response.PostResponse;

import com.example.testingproject.controller.response.UserResponse.UserResponse;
import com.example.testingproject.entity.Post;
import lombok.Data;

@Data
public class FindAllPostsResponse {
    Integer id;
    String title;
    String content;
    UserResponse user;



    public FindAllPostsResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        user = new UserResponse(post.getUser());
    }
}
