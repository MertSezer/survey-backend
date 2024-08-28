package com.survey.polla.model;

import java.util.List;

public class Survey {
    private Long id;
    private String title;
    private String description;
    private long beginningDate;
    private long endingDate;
    private String pictureURL;
    private User publisherUser;
    private List<User> accessedUsers;
    private List<User> likedUsers;
    private int likeCount;
    private List<String> hashtags;
    private List<Comment> comments;
    private List<Choice> choices;

}
