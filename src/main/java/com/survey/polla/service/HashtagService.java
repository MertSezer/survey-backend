package com.survey.polla.service;

import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.model.exception.HashtagAlreadyExistsException;
import com.survey.polla.model.exception.HashtagNotFoundException;
import com.survey.polla.model.exception.SurveyExistsforHashtagException;

import java.util.List;

public interface HashtagService {
    List<Hashtag> getAll();
    Hashtag getHashtagById(Long id);
    Hashtag saveHashtag(Hashtag hashtag) throws HashtagAlreadyExistsException;

    Hashtag updateHashtag(Hashtag hashtag) throws HashtagNotFoundException;

    void removeHashtag(Long id) throws HashtagNotFoundException, SurveyExistsforHashtagException;
}
