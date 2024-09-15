package com.survey.polla.model.dto;

import com.survey.polla.model.entity.Hashtag;

import java.util.List;

public class SurveyDto {
    private Long id;
    private String title;
    private String description;
    private long beginningDate;
    private long endingDate;
    private String pictureURL;
    private UserDto publisherUser;
    private List<HashtagDto> hashtags;
    private int likeCount;
    private List<CommentDto> comments;
    private List<ChoiceDto> choices;

    public SurveyDto() {
    }

    public SurveyDto(Long id, String title, String description, long beginningDate, long endingDate, String pictureURL, UserDto publisherUser, List<HashtagDto> hashtags, int likeCount, List<CommentDto> comments, List<ChoiceDto> choices) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.pictureURL = pictureURL;
        this.publisherUser = publisherUser;
        this.hashtags = hashtags;
        this.likeCount = likeCount;
        this.comments = comments;
        this.choices = choices;
    }

    public long getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(long beginningDate) {
        this.beginningDate = beginningDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(long endingDate) {
        this.endingDate = endingDate;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public UserDto getPublisherUser() {
        return publisherUser;
    }

    public void setPublisherUser(UserDto publisherUser) {
        this.publisherUser = publisherUser;
    }

    public List<HashtagDto> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashtagDto> hashtags) {
        this.hashtags = hashtags;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<ChoiceDto> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDto> choices) {
        this.choices = choices;
    }


}
