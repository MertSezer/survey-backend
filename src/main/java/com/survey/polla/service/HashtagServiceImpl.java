package com.survey.polla.service;

import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public List<Hashtag> getAll() {
        // 1. Repository inject edilmeli. (new)
        // 2. repository.findAll(); methodunu burada kullan ve dön.
        // // cntr + alt + V
        List<Hashtag> result = hashtagRepository.findAll();
        return result;
    }
}


