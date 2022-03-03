package com.example.testingproject.controller;

import com.example.testingproject.controller.response.FindAllPostsResponse;
import com.example.testingproject.entity.Post;
import com.example.testingproject.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    void save(@RequestBody Post post) {
        postService.save(post);
    }

    @GetMapping
    List<FindAllPostsResponse> findAll() {
        return postService.findAll();
    }
}
