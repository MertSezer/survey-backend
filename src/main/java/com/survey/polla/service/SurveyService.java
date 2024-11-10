package com.survey.polla.service;

import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.exception.NoCommentShouldBeProvidedException;
import com.survey.polla.model.exception.HashtagNotProvidedException;
import com.survey.polla.model.exception.NotEnoughChoicesException;

import java.util.List;

public interface SurveyService {
    void initialize();

    List<Survey> getSurveysByHashtagId(Long hashtagId);

    Survey getSurveyById(Long id);

    List<Survey> getAllSurveys();

    Survey create(Survey survey) throws HashtagNotProvidedException, NotEnoughChoicesException, NoCommentShouldBeProvidedException;
}