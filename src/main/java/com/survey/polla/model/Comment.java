package com.survey.polla.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User user;
    @Column(name = "numberOfViolations", nullable = true)
    public int numberOfViolations;
    @Column(name = "numberOfLikes", nullable = true)
    public int numberOfLikes;
    @Column(name = "releasedDate", nullable = false)
    public long releasedDate;
    @Column(name = "text", nullable = false)
    public String text;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;
}
