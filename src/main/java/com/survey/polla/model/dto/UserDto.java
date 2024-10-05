package com.survey.polla.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDto {
    @Schema(
            description = "Database id of the User.",
            name = "id",
            type = "Long",
            example = "1928")
    private Long id;

    @Schema(
            description = "Name of the User.",
            name = "name",
            type = "String",
            example = "Ali")
    private String name;
    @Schema(
            description = "Surname of the User.",
            name = "surname",
            type = "String",
            example = "Yılmaz")
    private String surname;
    @Schema(
            description = "user name  (nickname) of the User. This field should be unique.",
            name = "userName",
            type = "String",
            example = "aliyilmaz")
    private String userName;

    public UserDto() {
    }

    public UserDto(Long id, String name, String surname, String userName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
