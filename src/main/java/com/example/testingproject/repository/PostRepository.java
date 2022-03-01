package com.example.testingproject.repository;

import com.example.testingproject.entity.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);

    List<Post> findAll();
}
