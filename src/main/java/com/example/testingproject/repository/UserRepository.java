package com.example.testingproject.repository;

import com.example.testingproject.entity.User;

import java.util.List;

/*
TODO
 create new methods
 */

public interface UserRepository {

    List<User> findAll();

    User save(User user);
}
