package com.example.testingproject.service;

import com.example.testingproject.controller.request.UserRequest.SaveUserRequest;
import com.example.testingproject.controller.request.UserRequest.UpdateUserRequest;
import com.example.testingproject.converter.UserConverter;
import com.example.testingproject.entity.User;
import com.example.testingproject.repository.UserRepository;
import com.example.testingproject.service.exceptions.UserExceptions.NoUserException;
import com.example.testingproject.service.exceptions.UserExceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        void findUser_byEmail_emailNotExists_shouldThrowUserNoUserException() {
            // given
            final String email = "email";
            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(false);

            // then
            assertThatThrownBy(() -> {
                userService.findUser(email);
            }).isInstanceOf(NoUserException.class);
        }

        @Test
        void findUser_byEmail_emailExists_shouldReturnUser() throws UserAlreadyExistsException {
            // given
            final String name = "a";
            final String surname = "b";
            final String email = "c";
            final String desc = "d";

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
            when(userRepository.existsByEmail(email)).thenReturn(false);
            when(userConverter.toUser(saveUserRequest)).thenReturn(user);
            userService.save(saveUserRequest);

            // then
            verify(userRepository, times(0)).findByEmail(email);
        }

        @Test
        void findUser_byId_idNotExists_shouldThrowNoUserException () {
            // given
            final String data = "1";
            final Integer id = 1;

            // when
            when(userRepository.existsById(id)).thenReturn(false);

            // then
            assertThatThrownBy(() -> {
                userService.findUser(data);
            }).isInstanceOf(NoUserException.class);
        }

        @Test
        void findUser_byId_idExists_shouldReturnUser() throws UserAlreadyExistsException {
            // given
            final Integer id = 1;
            final String name = "a";
            final String surname = "b";
            final String email = "c";
            final String desc = "d";

            final User user = new User();
            user.setId(id);
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
            when(userRepository.existsById(id)).thenReturn(false);
            when(userConverter.toUser(saveUserRequest)).thenReturn(user);
            userService.save(saveUserRequest);

            // then
            verify(userRepository, times(0)).findById(id);
        }
    }

    @Nested
    class delete {
        @Test
        void deleteUser_byEmail_emailNotExists_shouldThrowUserNoUserException() {
            // given
            final String email = "email";

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(false);

            // then
            assertThatThrownBy(() -> {
                userService.delete(email);
            }).isInstanceOf(NoUserException.class);
        }

        @Test
        void deleteUser_byId_idNotExists_shouldThrowNoUserException () {
            // given
            final String data = "1";
            final Integer id = 1;

            // when
            when(userRepository.existsById(anyInt())).thenReturn(false);

            // then
            assertThatThrownBy(() -> {
                userService.delete(data);
            }).isInstanceOf(NoUserException.class);
        }

        @Test
        void deleteUser_byEmail_emailExists_shouldDeleteUser() throws UserAlreadyExistsException {
            // given
            final String name = "a";
            final String surname = "b";
            final String email = "c";
            final String desc = "d";

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
            when(userRepository.existsByEmail(email)).thenReturn(false);
            when(userConverter.toUser(saveUserRequest)).thenReturn(user);
            userService.save(saveUserRequest);

            // then
            verify(userRepository, times(0)).delete(user);
        }

        @Test
        void deleteUser_byId_idExists_shouldDeleteUser() throws UserAlreadyExistsException {
            // given
            final Integer id = 1;
            final String name = "a";
            final String surname = "b";
            final String email = "c";
            final String desc = "d";

            final User user = new User();
            user.setId(id);
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
            when(userRepository.existsById(id)).thenReturn(false);
            when(userConverter.toUser(saveUserRequest)).thenReturn(user);
            userService.save(saveUserRequest);

            // then
            verify(userRepository, times(0)).delete(user);
        }
    }

    @Nested
    class update {
        @Test
        void updateUser_emailDoesNotExist_shouldReturnNoUserException() {
            // given
            final UpdateUserRequest newUser = new UpdateUserRequest();
            final String email = "a";

            // when
            when(userRepository.existsByEmail(email)).thenReturn(false);

            //then
            assertThatThrownBy(()-> {
                userService.delete(email);
            }).isInstanceOf(NoUserException.class);
        }

        /*
        @Test
        void updateUser_emailDoesExist_shouldUpdateUser() throws UserWithGivenEmailNotExist, UserAlreadyExistsException {
            // given
            final String name = "a";
            final String surname = "a";
            final String description = "a";
            final String email = "asdasda";


            final UpdateUserRequest updateUserRequest = new UpdateUserRequest();
            updateUserRequest.setName(name);
            updateUserRequest.setSurname(surname);
            updateUserRequest.setDescription(description);

            final User user = new User();
            user.setName("ab");
            user.setSurname("abc");
            user.setDescription("abcd");
            user.setEmail(email);

            final SaveUserRequest saveUserRequest = new SaveUserRequest();
            saveUserRequest.setName(name);
            saveUserRequest.setSurname(surname);
            saveUserRequest.setEmail(email);
            saveUserRequest.setDescription(description);

            // when
            when(userConverter.toUser(updateUserRequest)).thenReturn(user);
            when(userRepository.existsByEmail(email)).thenReturn(false);
            userService.save(saveUserRequest);
            userService.update(updateUserRequest);

            // then
            boolean expected = user.getName().equals(updateUserRequest.getName()) &&
                    user.getSurname().equals(updateUserRequest.getSurname()) &&
                    user.getDescription().equals(updateUserRequest.getDescription());

            assertThat(expected).isTrue();

        }

         */
    }

    /*
    @Nested
    class updateEmail {

    }
    */
}