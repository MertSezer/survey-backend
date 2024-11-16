package com.survey.polla.controller;

import com.survey.polla.converter.UserConverter;
import com.survey.polla.model.dto.ChangePasswordDto;
import com.survey.polla.model.dto.LoginDto;
import com.survey.polla.model.dto.SignUpDto;
import com.survey.polla.model.dto.UserDto;
import com.survey.polla.model.entity.User;
import com.survey.polla.model.exception.DatabaseException;
import com.survey.polla.model.exception.PasswordExistsException;
import com.survey.polla.model.exception.PasswordNotValidException;
import com.survey.polla.model.exception.UserExistsException;
import com.survey.polla.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "User API", description = "User related API located here.")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @Operation(summary = "Getting user by user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is returned successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "User is not found.", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto result = userConverter.toDto(user);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Used to return all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all users successfully. There is no users in the list, return empty list.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginDto.class))})
    })
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = userService.getAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = userConverter.toDto(user);
            result.add(userDto);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<Boolean> login(@PathVariable String email, @PathVariable String password) {
        Boolean result = userService.login(email, password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @Operation(summary = "Used to login system securely.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login the system securely.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginDto.class))}),
            @ApiResponse(responseCode = "400", description = "Password or e-mail is blank. If password does not contains special characters, at least one number and at least one capital letter.",
                    content = @Content)})
    @PostMapping("/secure-login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto loginDto) {
        if (loginDto.getEmail() == null || loginDto.getPassword() == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        boolean isLoggedIn = userService.login(loginDto.getEmail(), loginDto.getPassword());
        ResponseEntity<Boolean> resultEntity;
        if (isLoggedIn) {
            resultEntity = new ResponseEntity<>(isLoggedIn, HttpStatus.OK);
        } else {
            resultEntity = new ResponseEntity<>(isLoggedIn, HttpStatus.BAD_REQUEST);
        }
        return resultEntity;
    }

    @Operation(summary = "Sign-up a new user. Password should include 8 characters at least, and it contains upper and under-case characters and special characters such as: \"*?_\"")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is signed up successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "User is already created before.", content = @Content)})
    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signedUp(@RequestBody SignUpDto signUpDto) {
        boolean isSigned = false;
        if (signUpDto.getPassword().length() < 8) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        User convertedUser = userConverter.toEntity(signUpDto);
        try {
            isSigned = userService.signUp(convertedUser);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(isSigned, HttpStatus.OK);
    }

    @Operation(summary = "Changing the password of user through parameter ChangePasswordDto changePasswordDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password is used successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Password is not valid or password already exists.", content = @Content)})
    @PostMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        ResponseEntity<Boolean> responseEntity;
        try {
            boolean isPasswordChanged = userService.changePassword(changePasswordDto.getUserId(), changePasswordDto.getNewPassword());
            if (isPasswordChanged) {
                responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (PasswordExistsException |
                 PasswordNotValidException e) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        } catch (DatabaseException e) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
