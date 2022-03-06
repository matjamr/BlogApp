package com.example.testingproject.controller;

import com.example.testingproject.controller.request.UserRequest.SaveUserRequest;
import com.example.testingproject.controller.request.UserRequest.UpdateEmailRequest;
import com.example.testingproject.controller.request.UserRequest.UpdateUserRequest;
import com.example.testingproject.controller.response.PostResponse.FindUserResponse;
import com.example.testingproject.service.UserService;
import com.example.testingproject.service.exceptions.UserExceptions.EmailAlreadyTakenException;
import com.example.testingproject.service.exceptions.UserExceptions.UserAlreadyExistsException;
import com.example.testingproject.service.exceptions.UserExceptions.UserNotExistException;
import com.example.testingproject.service.exceptions.UserExceptions.UserWithGivenEmailNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    @PutMapping()
    void updateUser(@RequestBody @Valid final UpdateUserRequest newUser) {
        try {
            userService.update(newUser);
        } catch (UserWithGivenEmailNotExist e) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND, "Invalid email", e
           );
        }
    }

    @PatchMapping("/{id}")
    void updateEmail(@RequestBody final UpdateEmailRequest request, @PathVariable Integer id) {
        try {
            userService.updateEmail(request, id);
        } catch (EmailAlreadyTakenException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Email is already taken", e
            );
        }
    }
}
