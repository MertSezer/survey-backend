package com.survey.polla.service;

import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.exception.CommentAlreadyExistsException;
import com.survey.polla.model.exception.CommentNotFoundException;
import com.survey.polla.model.exception.DatabaseException;
import com.survey.polla.model.exception.SurveyNotFoundException;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment, long surveyId) throws SurveyNotFoundException, CommentAlreadyExistsException;

    Comment updateComment(Comment comment) throws CommentNotFoundException;

    void deleteComment(long commentId) throws CommentNotFoundException, DatabaseException;

    List<Comment> getAllComments() throws DatabaseException;
}
