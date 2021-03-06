package com.example.testingproject.service;

import com.example.testingproject.controller.request.UserRequest.SaveUserRequest;
import com.example.testingproject.controller.request.UserRequest.UpdateEmailRequest;
import com.example.testingproject.controller.request.UserRequest.UpdateUserRequest;
import com.example.testingproject.controller.response.UserResponse.FindUserResponse;
import com.example.testingproject.converter.UserConverter;
import com.example.testingproject.entity.User;
import com.example.testingproject.repository.UserRepository;
import com.example.testingproject.service.exceptions.UserExceptions.EmailAlreadyTakenException;
import com.example.testingproject.service.exceptions.UserExceptions.UserAlreadyExistsException;
import com.example.testingproject.service.exceptions.UserExceptions.NoUserException;
import com.example.testingproject.service.exceptions.UserExceptions.UserWithGivenEmailNotExist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public FindUserResponse findUser(final String data) throws NoUserException {
        // data -> email or id
        // Checks if data is number
        if (!data.chars().allMatch(Character::isDigit)) {
            if(!userRepository.existsByEmail(data))
                throw new NoUserException("User with email: " + data + " does not exist");
            return userConverter.toDto(userRepository.findByEmail(data).get());
        }
        Integer id = Integer.parseInt(data);
        if(!userRepository.existsById(id))
            throw new NoUserException("User with id: " + id + " does not exist");
        return userConverter.toDto(userRepository.findById(id).get());
    }

    public void delete(final String data) throws NoUserException {
        User user;
        if (!data.chars().allMatch(Character::isDigit)) {
            if(!userRepository.existsByEmail(data))
                throw new NoUserException("User with email: " + data + " does not exist");
            user = userRepository.findByEmail(data).get();
        } else {
            Integer id = Integer.parseInt(data);
            if (!userRepository.existsById(id))
                throw new NoUserException("User with id: " + id + " does not exist");
            user = userRepository.findById(id).get();
        }
        userRepository.delete(user);
    }


    public void update(final UpdateUserRequest newUser) throws UserWithGivenEmailNotExist {
        if(!userRepository.existsByEmail(newUser.getEmail())) {
            throw new UserWithGivenEmailNotExist("There is no user with email: " + " email");
        }

        User user = userRepository.findByEmail(newUser.getEmail()).get();

        userRepository.save(userConverter.toUser(newUser, user));
    }

    // test
    public void updateEmail(final UpdateEmailRequest emailRequest, final Integer id) throws EmailAlreadyTakenException {
        if(userRepository.existsByEmail(emailRequest.getEmail())) {
            throw new EmailAlreadyTakenException("Email is already in use!");
        }
        User user = userRepository.findById(id).get();
        user.setEmail(user.getEmail());
        userRepository.save(user);
    }
}
