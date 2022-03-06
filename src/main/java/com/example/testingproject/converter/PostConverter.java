package com.example.testingproject.converter;

import com.example.testingproject.controller.request.PostRequest.SavePostRequest;
import com.example.testingproject.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    public Post toPost(final SavePostRequest request) {
        final Post post = new Post();
        post.setContent(request.getContent());
        post.setTitle(request.getTitle());
        post.setUser(request.getUser());

        return post;
    }
}
