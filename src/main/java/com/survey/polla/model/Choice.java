package com.survey.polla.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "choice")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    //TODO: version 2
    //private List<User> voters;

    @Column(name = "numberOfVotes", nullable = true)
    private int numberOfVotes;
    @Column(name = "percentile", nullable = true)
    public int percentile;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;
}