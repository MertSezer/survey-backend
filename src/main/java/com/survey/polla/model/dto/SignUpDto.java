package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SignUpDto {
    @Schema(
            description = "userName of signed user.",
            name = "userName",
            type = "String",
            example = "MertSez")
    private String userName;
    @Schema(
            description = "Profile picture of signed user.",
            name = "profilePictureURL",
            type = "String",
            example = "C:\\Pictures\\mertpic.jpg")
    private String profilePictureURL;
    @Schema(
            description = "Name of signed user.",
            name = "name",
            type = "String",
            example = "Mert")
    private String name;
    @Schema(
            description = "Surname of signed user.",
            name = "surname",
            type = "String",
            example = "Sez")
    private String surname;
    @Schema(
            description = "Password of signed user.",
            name = "password",
            type = "String",
            example = "123a*Ab")
    private String password;
    @Schema(
            description = "Birthdate of signed user.",
            name = "birthDate",
            type = "String",
            example = "1731242103127")
    private long birthDate;
    @Schema(
            description = "E-mail of signed user.",
            name = "email",
            type = "String",
            example = "abc@gmail.com")
    private String email;
    @Schema(
            description = "Phone number of signed user.",
            name = "phoneNumber",
            type = "String",
            example = "+905321234567")
    private String phoneNumber;

    public SignUpDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

