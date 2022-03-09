package com.example.testingproject.repository.sql;

import com.example.testingproject.entity.Comment;
import com.example.testingproject.repository.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {
}
