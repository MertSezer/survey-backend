package com.survey.polla.model.dto;

import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.entity.User;

public class CommentDto {
    private Long id;
    private UserDto user;
    public int numberOfViolations;
    public int numberOfLikes;
    public long releasedDate;
    public String text;
    private SurveyBasicDto survey;

    public CommentDto(Long id, UserDto user, int numberOfViolations, int numberOfLikes, long releasedDate, String text, SurveyBasicDto survey) {
        this.id = id;
        this.user = user;
        this.numberOfViolations = numberOfViolations;
        this.numberOfLikes = numberOfLikes;
        this.releasedDate = releasedDate;
        this.text = text;
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public int getNumberOfViolations() {
        return numberOfViolations;
    }

    public void setNumberOfViolations(int numberOfViolations) {
        this.numberOfViolations = numberOfViolations;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public long getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(long releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SurveyBasicDto getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyBasicDto survey) {
        this.survey = survey;
    }
}
