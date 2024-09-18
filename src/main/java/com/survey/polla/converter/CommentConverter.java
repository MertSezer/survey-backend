package com.survey.polla.converter;

import com.survey.polla.model.dto.CommentDto;
import com.survey.polla.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    @Autowired
    private UserConverter userConverter;

    public Comment toEntity(CommentDto commentDto)
    {
        Comment comment = new Comment();
        comment.setId(comment.getId());
        comment.setText(comment.getText());
        //TODO: Volkan açıkla: comment.setSurvey(commentDto.getSurvey());
        comment.setUser(userConverter.toEntity(commentDto.getUser()));
        comment.setReleasedDate(commentDto.getReleasedDate());
        comment.setNumberOfLikes(commentDto.getNumberOfLikes());
        comment.setNumberOfViolations(commentDto.getNumberOfViolations());

        return comment;
    }
    public CommentDto toDto(Comment comment)
    {
        CommentDto commentDto = new CommentDto();
        // TODO: volkan acıkla commentDto.setSurvey(comment.getSurvey());
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setUser(userConverter.toDto(comment.getUser()));
        commentDto.setReleasedDate(comment.getReleasedDate());
        commentDto.setNumberOfLikes(comment.getNumberOfLikes());
        commentDto.setNumberOfViolations(comment.getNumberOfViolations());
        return commentDto;
    }

    public UserConverter getUserConverter() {
        return userConverter;
    }

    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }
}

