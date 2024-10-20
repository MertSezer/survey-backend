package com.survey.polla.service;


import com.survey.polla.model.entity.User;
import com.survey.polla.model.exception.*;
import com.survey.polla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private static final int MIN_PASSWORD_LENGTH = 5;
    private static final String PASSWORD_PATTERN =
            "^(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        // Note that cnt + alt + L code beautify.
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }


    @Override
    public boolean login(String email, String password) {
        User u = userRepository.findByEmail(email);
        if (u != null) {
            return u.getPassword().equals(password);
        } else {
            return false;
        }
    }

    @Override
    public boolean signUp(User incomingUser) throws UserExistsException {
        User userEntity = userRepository.findByEmail(incomingUser.getEmail());
        User userEntity2 = userRepository.findByUserName(incomingUser.getUserName());
        if (userEntity != null || userEntity2 != null) {
            throw new UserExistsException();
        } else {
            try {
                User signedUser = userRepository.save(incomingUser);
            } catch (Exception exception) {
                System.out.println("ERROR: DATABASE REGISTRY OF USER: " + exception.getMessage());
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean changePassword(long userId, String password) throws PasswordExistsException, PasswordLengthException, PasswordDoesNotContainDigitException, PasswordDoesNotContainSpecialCharacterException, DatabaseException {
        User user = getUserById(userId);
        if (password.equals(user.getPassword())) {
            throw new PasswordExistsException("Password exists");
        }
        int len = password.length();

        if (len < MIN_PASSWORD_LENGTH) {
            throw new PasswordLengthException("Error: Password length is " + len + ". It should be greater than " + MIN_PASSWORD_LENGTH + ".");
        }

        boolean isContainsDigit = password.matches(".*\\d.*");
        if (!isContainsDigit) {
            throw new PasswordDoesNotContainDigitException("Error: Password does not contain digit.");
        }
        /*
        TODO: find special character algorithm
        Matcher matcher = pattern.matcher(password);
        boolean containsSpecialCharacter = matcher.matches();
        if (!containsSpecialCharacter) {
            throw new PasswordDoesNotContainSpecialCharacterException("Error: Password does not contain special character.");
        }*/

        try {
            user.setPassword(password);
            user = userRepository.save(user);
        } catch (Exception ex) {
            throw new DatabaseException("User couldn't be saved.");
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
