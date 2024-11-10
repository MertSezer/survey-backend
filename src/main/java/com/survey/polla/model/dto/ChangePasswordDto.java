package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChangePasswordDto {
    @Schema(
            description = "userId of changed password.",
            name = "userId",
            type = "Long",
            example = "123")
    private long userId;
    @Schema(
            description = "definition(password) of changed password.",
            name = "newPassword",
            type = "String",
            example = "KVNBU3")
    private String newPassword;

    public ChangePasswordDto(long userId, String newPassword) {
        this.userId = userId;
        this.newPassword = newPassword;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
