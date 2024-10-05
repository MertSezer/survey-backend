package com.survey.polla.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hashtag")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "begin_date", nullable = false)
    private long beginDate;
    @Column(name = "end_date", nullable = true)
    private long endDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<Survey> surveys;
    //survey


    public Hashtag() {
    }

    public Hashtag(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
}