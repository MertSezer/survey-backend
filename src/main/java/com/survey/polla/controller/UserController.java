package com.survey.polla.controller;

import com.survey.polla.model.dto.UserDto;
import com.survey.polla.model.entity.User;
import com.survey.polla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

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

}
