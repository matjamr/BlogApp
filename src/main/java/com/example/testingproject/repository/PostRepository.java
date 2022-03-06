package com.example.testingproject.repository;

import com.example.testingproject.entity.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    List<Post> findAll();
    Optional<Post> findById(Integer id);


    @Query("select case when count(p) > 0 then true else false end from Post p where " +
            "lower(p.title) like lower(:title) or lower(p.content) like lower(:content)")
    boolean exists(String title, String content);

    boolean existsById(Integer id);

    void deleteById(Integer id);

}
