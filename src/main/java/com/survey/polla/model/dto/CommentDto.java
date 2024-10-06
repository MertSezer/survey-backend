package com.survey.polla.model.dto;

public class CommentDto {
    public int numberOfViolations;
    public int numberOfLikes;
    public long releasedDate;
    public String text;
    private Long id;
    private UserDto user;
    private long surveyId;

    public CommentDto() {
    }

    public CommentDto(Long id, UserDto user, int numberOfViolations, int numberOfLikes, long releasedDate, String text, long surveyId) {
        this.id = id;
        this.user = user;
        this.numberOfViolations = numberOfViolations;
        this.numberOfLikes = numberOfLikes;
        this.releasedDate = releasedDate;
        this.text = text;
        this.surveyId = surveyId;
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

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }
}
