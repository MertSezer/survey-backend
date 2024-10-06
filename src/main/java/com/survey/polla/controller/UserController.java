package com.survey.polla.controller;

import com.survey.polla.converter.UserConverter;
import com.survey.polla.model.dto.ChangePasswordDto;
import com.survey.polla.model.dto.LoginDto;
import com.survey.polla.model.dto.SignUpDto;
import com.survey.polla.model.dto.UserDto;
import com.survey.polla.model.entity.User;
import com.survey.polla.model.expection.*;
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
        // TODO: loginDto.getEmail().equals(null) why always false? Check that.
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

    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signedUp(@RequestBody SignUpDto signUpDto) {
        boolean isSigned = false;
        User convertedUser = userConverter.toEntity(signUpDto);
        try {
            isSigned = userService.signUp(convertedUser);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(isSigned, HttpStatus.OK);
    }

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
        } catch (PasswordExistsException | PasswordLengthException | PasswordDoesNotContainDigitException |
                 PasswordDoesNotContainSpecialCharacterException e) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        } catch (DatabaseException e) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
