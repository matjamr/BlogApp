package com.example.testingproject.service;

import com.example.testingproject.entity.User;
import com.example.testingproject.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    TODO
     create interface for service and implement it here
     create methods for other types of requests
     create exceptions and their handling
     check whether it is better to use response entity / entity itself
 */

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
