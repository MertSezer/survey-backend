package com.survey.polla.model;

import jakarta.persistence.*;

@Entity
@Table(name = "polla_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_name", nullable = true)
    private String userName;
    @Column(name = "profile_picture_url", nullable = true)
    private String profilePictureURL;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "surname", nullable = true)
    private String surname;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "birth_date", nullable = true)
    private String birthDate;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number", nullable = true)
    private int phoneNumber;
    @Column(name = "country", nullable = true)
    private String country;
    @Column(name = "city", nullable = true)
    private String city;
    //TODO: city and country değişirse ankete etkisini nasıl çözmeliyiz.
    @Column(name = "authentication_password", nullable = true)
    private String authenticationPassword;
    @Column(name = "authentication_code", nullable = true)
    private int authenticationCode;
    @Column(name = "security_question", nullable = true)
    private String securityQuestion;
}
