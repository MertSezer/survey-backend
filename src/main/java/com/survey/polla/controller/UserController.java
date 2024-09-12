package com.survey.polla.controller;

import com.survey.polla.model.dto.UserDto;
import com.survey.polla.model.entity.User;
import com.survey.polla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
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
        UserDto result = new UserDto(user.getId(), user.getName(), user.getSurname());
        return ResponseEntity.ok(result);
    }
    /*

@GetMapping("/foos/{id}")
    @ResponseBody
    public String getFooById(@PathVariable String id) {
        return "ID: " + id;
    }
     */
}
