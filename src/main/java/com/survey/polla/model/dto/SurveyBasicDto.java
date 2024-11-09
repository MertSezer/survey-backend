package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SurveyBasicDto {
    @Schema(
            description = "Id of the survey.",
            name = "id",
            type = "Long",
            example = "5")
    private Long id;
    @Schema(
            description = "Title of the survey.",
            name = "title",
            type = "String",
            example = "Türkiye Ekonomisi Nasıl?")
    private String title;
    @Schema(
            description = "Beginning date of the survey.",
            name = "beginningDate",
            type = "Long",
            example = "1731147101735")
    private Long beginningDate;
    @Schema(
            description = "Like count of the survey.",
            name = "likeCount",
            type = "Integer",
            example = "112")
    private int likeCount;

    public SurveyBasicDto() {
    }

    public SurveyBasicDto(Long id, String title, Long beginningDate, int likeCount) {
        this.id = id;
        this.title = title;
        this.beginningDate = beginningDate;
        this.likeCount = likeCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Long beginningDate) {
        this.beginningDate = beginningDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
