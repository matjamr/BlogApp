package com.example.testingproject.service;

import com.example.testingproject.controller.request.SaveUserRequest;
import com.example.testingproject.controller.response.FindUserResponse;
import com.example.testingproject.converter.UserConverter;
import com.example.testingproject.entity.User;
import com.example.testingproject.repository.UserRepository;
import com.example.testingproject.service.exceptions.UserAlreadyExistsException;
import com.example.testingproject.service.exceptions.UserNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
    TODO
     create interface for service and implement it here
     create methods for other types of requests
     create exceptions and their handling
     check whether it is better to use response entity / entity itself
 */

@Service
public class UserService {
    final UserRepository userRepository;
    final UserConverter userConverter;


    public UserService(final UserRepository userRepository, final UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void save(final SaveUserRequest request) throws UserAlreadyExistsException {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExistsException("User with given email: " + request.getEmail() + " already exists");
        userRepository.save(userConverter.toUser(request));
    }

    public List<FindUserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(FindUserResponse::new)
                .collect(Collectors.toList());
    }

    public FindUserResponse findUser(final String data) throws UserNotExistException {
        // data -> email or id
        // Checks if data is number
        if (!data.chars().allMatch(Character::isDigit)) {
            if(!userRepository.existsByEmail(data))
                throw new UserNotExistException("User with email: " + data + " does not exist");
            return userConverter.toDto(userRepository.findByEmail(data).get());
        }
        Integer id = Integer.parseInt(data);
        if(!userRepository.existsById(id))
            throw new UserNotExistException("User with id: " + id + " does not exist");
        return userConverter.toDto(userRepository.findById(id).get());
    }

    public void delete(final String data) throws UserNotExistException {
        User user;
        if (!data.chars().allMatch(Character::isDigit)) {
            if(!userRepository.existsByEmail(data))
                throw new UserNotExistException("User with email: " + data + " does not exist");
            user = userRepository.findByEmail(data).get();
        } else {
            Integer id = Integer.parseInt(data);
            if (!userRepository.existsById(id))
                throw new UserNotExistException("User with id: " + id + " does not exist");
            user = userRepository.findById(id).get();
        }
        userRepository.delete(user);
    }

}
