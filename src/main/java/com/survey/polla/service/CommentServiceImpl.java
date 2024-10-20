package com.survey.polla.service;

import com.survey.polla.converter.CommentConverter;
import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.exception.CommentAlreadyExistsException;
import com.survey.polla.model.exception.CommentNotFoundException;
import com.survey.polla.model.exception.DatabaseException;
import com.survey.polla.model.exception.SurveyNotFoundException;
import com.survey.polla.repository.CommentRepository;
import com.survey.polla.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentConverter commentConverter;

    @Override
    public Comment createComment(Comment comment, long surveyId) throws SurveyNotFoundException, CommentAlreadyExistsException {

        long commentId = comment.getId();
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent())
        {
            throw new CommentAlreadyExistsException("Comment already exists");
        }
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if (optionalSurvey.isPresent()) {
            Survey survey = optionalSurvey.get();
            comment.setSurvey(survey);
            Comment savedcomment = commentRepository.save(comment);
            survey.getComments().add(savedcomment);
            survey = surveyRepository.save(survey);
            return savedcomment;
        } else {
            throw new SurveyNotFoundException("Survey does not exist");
        }
    }

    @Override
    public Comment updateComment(Comment updatedComment) throws CommentNotFoundException {
        Optional<Comment> foundedCommentOptional = commentRepository.findById(updatedComment.getId());
        if (foundedCommentOptional.isPresent()) {
            String newText = updatedComment.getText();
            Comment foundedComment = foundedCommentOptional.get();
            foundedComment.setText(newText);
            foundedComment = commentRepository.save(foundedComment);
            return foundedComment;
        } else {
            throw new CommentNotFoundException("Comment doesn't exist.");
        }
    }

    @Override
    public void deleteComment(long commentId) throws CommentNotFoundException, DatabaseException {
        Optional<Comment> optionalComment = commentRepository.findById(commentId); // cnt + alt + V (variable)
        if (!(optionalComment.isPresent())) {
            throw new CommentNotFoundException("Comment does not exist.");
        } else {
            Comment comment = optionalComment.get();
            try {
                commentRepository.delete(comment);
            } catch (Exception exception) {
                throw new DatabaseException("Couldn't delete from database.");

            }
        }
    }

    @Override
    public List<Comment> getAllComments() throws DatabaseException {
        List<Comment> all;
        try {
            all = commentRepository.findAll();
        } catch (Exception e) {
            throw new DatabaseException("Comment couldn't be accessed.");
        }
        return all;
    }

}
