package com.survey.polla.model;

public class User {
    private Long id;
    private String userName;
    private String profilePictureURL;
    private String name;
    private String surname;
    private String password;
    private String birthDate;
    private String email;
    private int phoneNumber;
    private String country;
    private String city;
    //TODO: city and country değişirse ankete etkisini nasıl çözmeliyiz.
    private String authenticationPassword;
    private int authenticationCode;
    private String securityQuestion;
}
