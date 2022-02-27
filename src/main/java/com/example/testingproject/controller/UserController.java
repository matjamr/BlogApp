package com.example.testingproject.controller;

import com.example.testingproject.entity.User;
import com.example.testingproject.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    TODO
     create methods for deleting, patch, put

*/


@RestController
@RequestMapping(path="/api/user", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json"})
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping
    List<User> findAll() {
        return userService.findAll();
    }
}
