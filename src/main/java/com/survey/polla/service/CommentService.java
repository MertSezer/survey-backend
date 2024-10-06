package com.survey.polla.service;

import com.survey.polla.model.dto.CommentDto;
import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.expection.SurveyNotFoundException;

public interface CommentService {
    Comment createComment(Comment comment, long surveyId) throws SurveyNotFoundException;
}
