package com.survey.polla.converter;

import com.survey.polla.model.dto.CommentDto;
import com.survey.polla.model.dto.CommentUpdatableDto;
import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.entity.User;
import com.survey.polla.service.SurveyService;
import com.survey.polla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    @Autowired
    private UserService userService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private UserConverter userConverter;

    public Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        long surveyId = commentDto.getSurveyId();
        Survey survey = surveyService.getSurveyById(surveyId);
        comment.setSurvey(survey);
        long userId = commentDto.getUserId();
        User user = userService.getUserById(userId);
        comment.setUser(user);
        comment.setReleasedDate(commentDto.getReleasedDate());
        comment.setNumberOfLikes(commentDto.getNumberOfLikes());
        comment.setNumberOfViolations(commentDto.getNumberOfViolations());
        return comment;
    }

    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setUserId(comment.getUser().getId());
        commentDto.setReleasedDate(comment.getReleasedDate());
        commentDto.setNumberOfLikes(comment.getNumberOfLikes());
        commentDto.setNumberOfViolations(comment.getNumberOfViolations());
        commentDto.setSurveyId(comment.getSurvey().getId());
        return commentDto;
    }

    public Comment toEntity(CommentUpdatableDto commentUpdatableDto) {
        Comment comment = new Comment();
        comment.setId(commentUpdatableDto.getId());
        comment.setText(commentUpdatableDto.getText());
        return comment;
    }


    /*
    public CommentUpdatableDto toDto(Comment comment) {
        CommentUpdatableDto commentUpdatableDto = new CommentUpdatableDto();
        commentUpdatableDto.setId(comment.getId());
        commentUpdatableDto.setText(comment.getText());
        return commentUpdatableDto;
    }
    */
}

