package com.survey.polla.model.dto;

public class HashtagDto {

    private Long id;

    private String text;

    private String description;

    public HashtagDto() {
    }

    public HashtagDto(Long id, String text, String description) {
        this.id = id;
        this.text = text;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
