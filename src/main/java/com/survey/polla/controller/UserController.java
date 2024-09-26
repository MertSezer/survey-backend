package com.survey.polla.controller;

import com.survey.polla.converter.UserConverter;
import com.survey.polla.model.dto.ChangePasswordDto;
import com.survey.polla.model.dto.LoginDto;
import com.survey.polla.model.dto.SignUpDto;
import com.survey.polla.model.dto.UserDto;
import com.survey.polla.model.entity.User;
import com.survey.polla.model.expection.*;
import com.survey.polla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            //sponseEntity v1 = new ResponseEntity<UserDto>(HttpStatusCode.valueOf(404));
            return ResponseEntity.notFound().build();
            //return v1;
        }
        UserDto result = new UserDto(user.getId(), user.getName(), user.getSurname(), user.getUserName());
        return ResponseEntity.ok(result);
    }

    /*

@GetMapping("/foos/{id}")
    @ResponseBody
    public String getFooById(@PathVariable String id) {
        return "ID: " + id;
    }
     */
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<Boolean> login(@PathVariable String email, @PathVariable String password) {
        Boolean result = userService.login(email, password);
        ResponseEntity responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/secure-login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto loginDto) {
        boolean isLoggedIn = userService.login(loginDto.getEmail(), loginDto.getPassword());
        ResponseEntity resultEntity;
        if (isLoggedIn) {
            resultEntity = new ResponseEntity<>(isLoggedIn, HttpStatus.OK);
        } else {
            resultEntity = new ResponseEntity<>(isLoggedIn, HttpStatus.BAD_REQUEST);
        }
        return resultEntity;
    }

    // signup
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
        ResponseEntity responseEntity;
        try {
            boolean isPasswordChanged = userService.changePassword(changePasswordDto.getUserId(), changePasswordDto.getNewPassword());
            if (isPasswordChanged) {
                responseEntity = new ResponseEntity(true, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (PasswordExistsException e) {
            responseEntity = new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        } catch (PasswordLengthException e) {
            responseEntity = new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        } catch (PasswordDoesNotContainDigitException e) {
            responseEntity = new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        } catch (PasswordDoesNotContainSpecialCharacterException e) {
            responseEntity = new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        } catch (DatabaseException e) {
            responseEntity = new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
