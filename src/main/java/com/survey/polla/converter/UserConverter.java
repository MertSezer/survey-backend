package com.survey.polla.converter;

import com.survey.polla.model.dto.SignUpDto;
import com.survey.polla.model.dto.UserDto;
import com.survey.polla.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setName(userDto.getName());
        user.setSurname(user.getSurname());
        user.setId(userDto.getId());
        return user;
    }

    // method overloading
    public User toEntity(SignUpDto signUpDto) {
      User user = new User();
      user.setUserName(signUpDto.getUserName());
      user.setName(signUpDto.getName());
      user.setSurname(signUpDto.getSurname());
      user.setPassword(signUpDto.getPassword());
      user.setBirthDate(signUpDto.getBirthDate());
      user.setPhoneNumber(signUpDto.getPhoneNumber());
      user.setProfilePictureURL(signUpDto.getProfilePictureURL());
      user.setEmail(signUpDto.getEmail());
      return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setId(user.getId());
        return userDto;
    }
}
