package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommentUpdatableDto {
    @Schema(
            description = "id of Comment Update process.",
            name = "id",
            type = "Long",
            example = "21400246")
    private Long id;
    @Schema(
            description = "Text of Comment Update process.",
            name = "text",
            type = "String",
            example = "Comment example text.")
    private String text;

    public CommentUpdatableDto() {
    }

    public CommentUpdatableDto(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

