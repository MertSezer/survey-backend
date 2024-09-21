package com.survey.polla.service;

import com.survey.polla.model.entity.User;

public interface UserService {
    User getUserById(Long id);

    boolean login(String email, String password);
}

