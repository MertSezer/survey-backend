package com.survey.polla.service;

import com.survey.polla.PollaApplication;
import com.survey.polla.model.*;
import com.survey.polla.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // @Component, @Configuration
public class SurveyServiceImpl implements SurveyService{
    @Autowired
    private SurveyRepository sr;

    public SurveyServiceImpl() {
        initialize();
    }

    public void initialize()
    {
        int likesCount = 10;
        Survey s = new Survey();
        User u = new User();
        u.setEmail("mertsezer1996319@gmail.com");
        u.setPassword("KVNBU1");
        u.setName("mert");
        u.setSurname("sezer");
        u.setUserName("mertSezer");
        User u2 = new User();
        u2.setEmail("nedimsezer1996319@gmail.com");
        u2.setPassword("KVNBU2");
        u2.setName("nedim");
        u2.setSurname("sezer");
        u2.setUserName("nedimSezer");
        List<Choice> choices = new ArrayList<>();
        Choice first = new Choice();
        first.setText("Türkiye ekonomisi iyi");
        first.setSurvey(s);
        first.setPercentile(0.5);
        first.setNumberOfVotes(50);
        Choice second = new Choice();
        second.setText("Türkiye ekonomisi kötü");
        second.setSurvey(s);
        second.setPercentile(0.5);
        second.setNumberOfVotes(50);
        choices.add(first);
        choices.add(second);
        s.setBeginningDate(System.currentTimeMillis());
        s.setEndingDate(System.currentTimeMillis());
        s.setDescription("Surveys_Description");
        s.setTitle("Türkiye ekonomisi nasıl?");
        s.setLikeCount(likesCount);
        s.setPictureURL("c:\\//a.jpeg");
        s.setChoices(choices);
        List<Hashtag> hashtags = new ArrayList<>();
        Hashtag h = new Hashtag("gündem");
        h.setDescription("gündem description");
        h.setBeginDate(System.currentTimeMillis());
        h.setEndDate(System.currentTimeMillis());
        h.setSurveys(List.of(s));
        hashtags.add(h);
        s.setHashtags(hashtags);
        Comment c1 = new Comment();
        Comment c2 = new Comment();
        c1.setText("firstyorum");
        c2.setText("secondyorum");
        c1.setNumberOfLikes(30);
        c1.setNumberOfViolations(3);
        c1.setUser(u);
        c1.setReleasedDate(System.currentTimeMillis());
        c1.setSurvey(s);
        c2.setNumberOfLikes(60);
        c2.setNumberOfViolations(6);
        c2.setUser(u);
        c2.setReleasedDate(System.currentTimeMillis());
        c2.setSurvey(s);
        s.setComments(List.of(c1, c2));
        s.setPublisherUser(u2);
        sr.save(s);
    }
}
