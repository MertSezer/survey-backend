package com.survey.polla.model.dto;

public class ChoiceDto {
    public double percentile;
    private Long id;
    private String text;
    private int numberOfVotes;
    private SurveyBasicDto survey;

    public ChoiceDto() {
    }

    public ChoiceDto(Long id, double percentile, String text, int numberOfVotes, SurveyBasicDto survey) {
        this.id = id;
        this.percentile = percentile;
        this.text = text;
        this.numberOfVotes = numberOfVotes;
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPercentile() {
        return percentile;
    }

    public void setPercentile(double percentile) {
        this.percentile = percentile;
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

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
