package com.example.testingproject.repository;

import com.example.testingproject.entity.User;

import java.util.List;
import java.util.Optional;

/*
TODO
 create new methods
 */

public interface UserRepository {

    List<User> findAll();

    User save(User user);

    boolean existsByEmail(String email);
    boolean existsById(Integer id);

    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);

    void delete(User user);
}
