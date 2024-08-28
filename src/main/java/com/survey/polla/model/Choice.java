package com.survey.polla.model;

import java.util.List;

public class Choice {
    private Long id;
    private String text;
    private List<User> users;
    private int numberOfVotes;
    public int percentile;
}