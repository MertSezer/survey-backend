package com.survey.polla.service;

import com.survey.polla.model.expection.*;
import com.survey.polla.model.entity.User;

public interface UserService {
    User getUserById(Long id);

    boolean login(String email, String password);

    boolean signUp(User user) throws UserExistsException;

    boolean changePassword(long userId, String password) throws PasswordExistsException, PasswordLengthException, PasswordDoesNotContainDigitException, PasswordDoesNotContainSpecialCharacterException, DatabaseException;
}

