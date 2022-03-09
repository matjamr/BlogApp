package com.example.testingproject.controller.response.PostResponse;

import com.example.testingproject.controller.response.commentResponse.CommentResponse;
import com.example.testingproject.entity.Post;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponse {
    private Integer id;
    private String title;
    private String content;
    private List<CommentResponse> comments;

    public PostResponse() {}

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = post.getComments().stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

}
