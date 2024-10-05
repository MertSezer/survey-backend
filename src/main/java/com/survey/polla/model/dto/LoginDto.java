package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank
    @Schema(
            description = "Password(must contain at least one letter, one number, one special character) of Login Dto",
            name = "password",
            type = "String",
            example = "KVNBU23;*")
    private String password;
    @NotBlank
    @Schema(
            description = "Email field of Login Dto",
            name = "email",
            type = "String",
            example = "mertsezer1996319@gmail.com")
    private String email;

    public LoginDto() {

    }

    public LoginDto(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
