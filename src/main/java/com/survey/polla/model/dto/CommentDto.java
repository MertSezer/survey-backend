package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommentDto {
    @Schema(
            description = "Number of violations of Comment Dto",
            name = "numberOfViolations",
            type = "int",
            example = "4")
    public int numberOfViolations;
    @Schema(
            description = "Number of likes of Comment Dto",
            name = "numberOfLikes",
            type = "int",
            example = "5")
    public int numberOfLikes;
    @Schema(
            description = "Released date Comment Dto",
            name = "releasedDate",
            type = "long",
            example = "1704135351653"
    )
    public long releasedDate;
    @Schema(
            description = "Written text of Comment Dto",
            name = "text",
            type = "String",
            example = "Text that is written for Comment Dto"
    )
    public String text;
    @Schema(
            description = "Id Comment Dto",
            name = "id",
            type = "Long",
            example = "2345678901"
    )
    private Long id;
    @Schema(
            description = "Id of User for Comment Dto",
            name = "userId",
            type = "Long",
            example = "15"
    )
    private long userId;
    @Schema(
            description = "Id of the Survey of Comment Dto",
            name = "surveyId",
            type = "Long",
            example = "16"
    )
    private long surveyId;

    public CommentDto() {
    }

    public CommentDto(Long id, long user, int numberOfViolations, int numberOfLikes, long releasedDate, String text, long surveyId) {
        this.id = id;
        this.userId = user;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
