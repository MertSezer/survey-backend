package com.survey.polla.service;

import com.survey.polla.model.dto.CommentDto;
import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.expection.SurveyNotFoundException;
import com.survey.polla.repository.CommentRepository;
import com.survey.polla.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment createComment(Comment comment, long surveyId) throws SurveyNotFoundException {
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if (optionalSurvey.isPresent())
        {
            Survey survey = optionalSurvey.get();
            Comment savedcomment = commentRepository.save(comment);
            survey.getComments().add(savedcomment);
            survey = surveyRepository.save(survey);
            return savedcomment;
        }
        else
        {
            throw new SurveyNotFoundException("Survey does not exist");
        }
    }
}
