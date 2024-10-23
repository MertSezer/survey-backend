package com.survey.polla.service;


import com.survey.polla.model.entity.User;
import com.survey.polla.model.exception.*;
import com.survey.polla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,10}$";

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
            // password=Sifre1234*
            String hashPassword = u.getPassword(); // klksd&530
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            boolean isPasswordMatches = bcrypt.matches(password, hashPassword);
            return isPasswordMatches; //true
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
    public boolean changePassword(long userId, String password) throws PasswordExistsException, PasswordNotValidException, DatabaseException {
        User user = getUserById(userId);
        if (password.equals(user.getPassword())) {
            throw new PasswordExistsException("Password exists");
        }
        Matcher passwordRuleMatcher = pattern.matcher(password);
        boolean isPasswordValid = passwordRuleMatcher.matches();
        if (!isPasswordValid) {
            throw new PasswordNotValidException("Error: Password can not be appropriate password rules.");
        }
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashPassword = passwordEncoder.encode(password);
            user.setPassword(hashPassword);
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
