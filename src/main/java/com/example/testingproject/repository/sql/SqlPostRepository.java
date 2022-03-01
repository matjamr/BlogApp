package com.example.testingproject.repository.sql;

import com.example.testingproject.entity.Post;
import com.example.testingproject.repository.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlPostRepository extends PostRepository, JpaRepository<Post, Integer> {
}
