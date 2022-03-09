package com.example.testingproject.repository;

import com.example.testingproject.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> findAll();
    Optional<Comment> findById(Integer integer);
    Comment save(Comment comment);

    boolean existsByContent(String content);
    boolean existsById(Integer id);

    void deleteById(Integer id);

}
