package com.survey.polla.service;

import com.survey.polla.model.entity.User;
import com.survey.polla.model.exception.*;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    boolean login(String email, String password);

    boolean signUp(User user) throws UserExistsException;

    boolean changePassword(long userId, String password) throws PasswordExistsException, PasswordNotValidException, DatabaseException;

    List<User> getAll();
}

