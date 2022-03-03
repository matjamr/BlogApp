package com.example.testingproject.controller;

import com.example.testingproject.controller.request.SaveUserRequest;
import com.example.testingproject.controller.response.FindUserResponse;
import com.example.testingproject.service.UserService;
import com.example.testingproject.service.exceptions.UserAlreadyExistsException;
import com.example.testingproject.service.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/*
    TODO
     create methods for deleting, patch, put
*/


@RestController
@RequestMapping(path="/api/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    void save(@RequestBody SaveUserRequest user) {
        try {
            userService.save(user);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "User with given email exists", e
            );
        }

    }

    @GetMapping("/{data}")
    FindUserResponse findUser(@PathVariable String data){
        try {
            return userService.findUser(data);
        } catch(UserNotExistException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found", e
            );
        }
    }

    @GetMapping
    List<FindUserResponse> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/{data}")
    void delete (@PathVariable String data){
        try {
            userService.delete(data);
        } catch(UserNotExistException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found", e
            );
        }
    }
}
