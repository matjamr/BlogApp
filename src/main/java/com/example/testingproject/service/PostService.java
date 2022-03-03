package com.example.testingproject.service;

import com.example.testingproject.controller.response.FindAllPostsResponse;
import com.example.testingproject.entity.Post;
import com.example.testingproject.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    final private PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public List<FindAllPostsResponse> findAll() {
        return postRepository.findAll().stream()
                .map(FindAllPostsResponse::new)
                .collect(Collectors.toList());
    }
}
