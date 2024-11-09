package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

public class SurveyDto {
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
            description = "Description of the survey.",
            name = "description",
            type = "String",
            example = "Türkiye Ekonomisi'nin durumunu analiz etmeye çalışan bir ankettir.")
    private String description;
    @Schema(
            description = "Beginning date of the survey.",
            name = "beginningDate",
            type = "Long",
            example = "1731147101735")
    private long beginningDate;
    @Schema(
            description = "Ending date of the survey.",
            name = "endingDate",
            type = "Long",
            example = "1731147101735")
    private long endingDate;
    @Schema(
            description = "Picture URL of the survey.",
            name = "pictureURL",
            type = "String",
            example = "C:\\surveyPicture1.jpg")
    private String pictureURL;
    @Schema(
            description = "Publisher user Id (which is registered the system before) of the survey.",
            name = "publisherUserId",
            type = "Long",
            example = "1")
    private Long publisherUserId;

    @Schema(
            description = "Hashtags id list (which is created the system before) of the survey.",
            name = "hashtagIds",
            type = "List",
            example = "[1, 2, 3]")
    private List<Long> hashtagIds;
    @Schema(
            description = "Like count of the survey.",
            name = "likeCount",
            type = "Integer",
            example = "112")
    private int likeCount;
    @Schema(
            description = "Comments of the survey.",
            name = "comments",
            type = "List",
            example = "[{\n" +
                    "  \"numberOfViolations\": 4,\n" +
                    "  \"numberOfLikes\": 5,\n" +
                    "  \"releasedDate\": 1704135351653,\n" +
                    "  \"text\": \"Text that is written for Comment Dto\",\n" +
                    "  \"id\": 2345678901,\n" +
                    "  \"userId\": 15,\n" +
                    "  \"surveyId\": 16\n" +
                    "}]")
    private List<CommentDto> comments = new ArrayList<>();
    @Schema(
            description = "Choices of the survey.",
            name = "choices",
            type = "List",
            example = "[İyi, Kötü]")
    private List<String> choices = new ArrayList<>();

    public SurveyDto() {
    }

    public SurveyDto(Long id, String title, String description, long beginningDate, long endingDate, String pictureURL, Long publisherUserId, List<Long> hashtagIds, int likeCount, List<CommentDto> comments, List<String> choices) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.pictureURL = pictureURL;
        this.publisherUserId = publisherUserId;
        this.hashtagIds = hashtagIds;
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

    public Long getPublisherUserId() {
        return publisherUserId;
    }

    public void setPublisherUserId(Long publisherUserId) {
        this.publisherUserId = publisherUserId;
    }

    public List<Long> getHashtagIds() {
        return hashtagIds;
    }

    public void setHashtagIds(List<Long> hashtagIds) {
        this.hashtagIds = hashtagIds;
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

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }


}
