package com.survey.polla.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

public class HashtagDto {
    @Schema(
            description = "Database id Of the Hashtag",
            name = "id",
            type = "Long",
            example = "21400246")
    @JsonIgnore // NOTE: API'de gözükmüyor.
    private Long id;

    @Schema(
            description = "Text Of the Hashtag",
            name = "text",
            type = "String",
            example = "AvrupaLigi")
    private String text;

    @Schema(
            description = "Description of the Hashtag",
            name = "description",
            type = "String",
            example = "Avrupa Ligi'nde bu hafta oynanan maç sonuçları tahminleri")
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
