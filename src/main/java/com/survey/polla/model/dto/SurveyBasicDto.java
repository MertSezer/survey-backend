package com.survey.polla.model.dto;

public class SurveyBasicDto {
    private Long id;
    private String title;
    private Long beginningDate;
    private int likeCount;

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
