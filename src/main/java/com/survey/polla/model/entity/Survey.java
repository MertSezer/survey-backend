package com.survey.polla.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User publisherUser;
    //TODO: accessedUsers, likedUsers version2'de ele alınacaktır.
    //private List<User> accessedUsers;
    //private List<User> likedUsers;
    @Column(name = "like_count", nullable = false)
    private int likeCount;
    @ManyToMany
    private List<Hashtag> hashtags;
    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Choice> choices;

    public Survey() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(long beginningDate) {
        this.beginningDate = beginningDate;
    }

    public long getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(long endingDate) {
        this.endingDate = endingDate;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public User getPublisherUser() {
        return publisherUser;
    }

    public void setPublisherUser(User publisherUser) {
        this.publisherUser = publisherUser;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
