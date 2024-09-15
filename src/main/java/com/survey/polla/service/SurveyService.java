package com.survey.polla.service;

import com.survey.polla.model.entity.Survey;

import java.util.List;

public interface SurveyService {
    void initialize();
    List<Survey> getSurveysByHashtagId(Long hashtagId);

    Survey getSurveyById(Long id);
}