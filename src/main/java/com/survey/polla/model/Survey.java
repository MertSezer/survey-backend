package com.survey.polla.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "beginning_date", nullable = false)
    private long beginningDate;
    @Column(name = "ending_date", nullable = true)
    private long endingDate;
    @Column(name = "picture_url", nullable = true)
    private String pictureURL;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User publisherUser;
    //TODO: accessedUsers, likedUsers version2'de ele alınacaktır.
    //private List<User> accessedUsers;
    //private List<User> likedUsers;
    @Column(name = "like_count", nullable = false)
    private int likeCount;
    //TODO: hashtags version 2'de bakılsın.
    //private List<String> hashtags;
    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Choice> choices;

}
