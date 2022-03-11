package com.example.testingproject.service;

import com.example.testingproject.controller.request.UserRequest.SaveUserRequest;
import com.example.testingproject.converter.UserConverter;
import com.example.testingproject.entity.User;
import com.example.testingproject.repository.UserRepository;
import com.example.testingproject.service.exceptions.UserExceptions.UserAlreadyExistsException;
import com.example.testingproject.service.exceptions.UserExceptions.UserNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

// Todo ADD DISPLAY MESSAGES TO EVERY TEST

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class save {

        @Test
        @DisplayName("Should throw UserNotExistException, because given email cannot be in database")
        void saveUser_UserWithGivenEmailExists_ShouldThrowUserAlreadyExistsException() {
            // given
            final SaveUserRequest saveUserRequest = new SaveUserRequest();
            saveUserRequest.setName("aasd");
            saveUserRequest.setSurname("asdasdax");
            saveUserRequest.setEmail("Email@cos");
            saveUserRequest.setDescription("Descriptiin");

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(true);

            // then
            assertThatThrownBy(() -> {
                userService.save(saveUserRequest);
            }).isInstanceOf(UserAlreadyExistsException.class);
        }

        @Test
        @DisplayName("Proper data should save in database")
        void findUser_UserWithGivenEmailNotExists_ShouldSaveInDatabase() throws UserAlreadyExistsException {

            // given
            final SaveUserRequest saveUserRequest = new SaveUserRequest();
            saveUserRequest.setName("aasd");
            saveUserRequest.setSurname("asdasdax");
            saveUserRequest.setEmail("Email@cos");
            saveUserRequest.setDescription("Descriptiin");

            final User user = new User();
            user.setName("aasd");
            user.setSurname("asdasdax");
            user.setEmail("Email@cos");
            user.setDescription("Descriptiin");

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(false);
            when(userConverter.toUser(saveUserRequest)).thenReturn(user);
            userService.save(saveUserRequest);

            // then
            verify(userRepository, times(1)).save(user);
        }
    }

    @Nested
    class findUser
    {
        @Test
        @DisplayName("")
        void findUser_byEmail_emailNotExists_shouldThrowUserNotExistsException() {
            // given
            final String email = "1";
            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(false);

            // then
            assertThatThrownBy(() -> {
                userService.findUser(email);
            }).isInstanceOf(UserNotExistException.class);
        }

        @Test
        @DisplayName("test")
        void findUser_byEmail_emailExists_shouldReturnUser() throws UserAlreadyExistsException {
            // given
            final String name = "a";
            final String surname = "a";
            final String email = "a";
            final String desc = "a";
            final LocalDateTime date = LocalDateTime.now();

            final User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setDescription(desc);

            final SaveUserRequest saveUserRequest = new SaveUserRequest();
            saveUserRequest.setName(name);
            saveUserRequest.setSurname(surname);
            saveUserRequest.setEmail(email);
            saveUserRequest.setDescription(desc);

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(false);
            when(userConverter.toUser(saveUserRequest)).thenReturn(user);
            userService.save(saveUserRequest);

            // then
            verify(userRepository, times(1)).findByEmail(email);

        }
    }
}