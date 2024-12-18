package com.survey.polla.service;

import com.survey.polla.model.entity.*;
import com.survey.polla.model.exception.NoCommentShouldBeProvidedException;
import com.survey.polla.model.exception.HashtagNotProvidedException;
import com.survey.polla.model.exception.NotEnoughChoicesException;
import com.survey.polla.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // @Component, @Configuration
public class SurveyServiceImpl implements SurveyService, InitializingBean {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private HashtagRepository hashtagRepository;
    @Autowired
    private CommentRepository commentRepository;


    /*
    NOTE: Tek tek @Autowired demek yerine constructor'da tüm gerekli bean'leri yaratabiliriz.
    @Autowired
    public SurveyServiceImpl(SurveyRepository sr, UserRepository userRepository) {
        this.sr = sr;
        this.userRepository = userRepository;
    }
    */
    // NOTE: InitializeBean interface'inden gelir.
    // Sınıfta yer alan tüm bean'lerin yüklendiğinden/yaratıldığından emin oluruz.
    @Override
    public void afterPropertiesSet() throws Exception {
        initialize();
    }


    @Override
    public void initialize() {
        User user1 = new User();
        user1.setEmail("mertsezer1996319@gmail.com");
        user1.setPassword("KVNBU1");
        user1.setName("mert");
        user1.setSurname("sezer");
        user1.setUserName("mertSezer");
        user1 = userRepository.save(user1);
        User u2 = new User();
        u2.setEmail("nedimsezer1996319@gmail.com");
        u2.setPassword("KVNBU2");
        u2.setName("nedim");
        u2.setSurname("sezer");
        u2.setUserName("nedimSezer");
        u2 = userRepository.save(u2);
        // --------------------------- user kaydedildi.
        int likesCount = 10;
        Survey survey = new Survey();
        survey.setBeginningDate(System.currentTimeMillis());
        survey.setEndingDate(System.currentTimeMillis());
        survey.setDescription("Surveys_Description");
        survey.setTitle("Türkiye ekonomisi nasıl?");
        survey.setLikeCount(likesCount);
        survey.setPictureURL("c:\\//a.jpeg");
        survey.setPublisherUser(user1);
        survey = surveyRepository.save(survey);

        List<Choice> choices = new ArrayList<>();
        Choice first = new Choice();
        first.setText("Türkiye ekonomisi iyi");
        first.setSurvey(survey);
        first.setPercentile(0.5);
        first.setNumberOfVotes(50);

        Choice second = new Choice();
        second.setText("Türkiye ekonomisi kötü");
        second.setSurvey(survey);
        second.setPercentile(0.5);
        second.setNumberOfVotes(50);
        first = choiceRepository.save(first);
        second = choiceRepository.save(second);
        choices.add(first);
        choices.add(second);
        survey.setChoices(choices);
        survey = surveyRepository.save(survey);

        List<Hashtag> hashtags = new ArrayList<>();
        Hashtag hashtag = new Hashtag("gündem");
        hashtag.setDescription("gündem description");
        hashtag.setBeginDate(System.currentTimeMillis());
        hashtag.setEndDate(System.currentTimeMillis());
        hashtag.setSurveys(List.of(survey));
        // hashtag db kaydet
        hashtag = hashtagRepository.save(hashtag);
        hashtags.add(hashtag);
        survey.setHashtags(hashtags);
        survey = surveyRepository.save(survey);

        Comment com1 = new Comment();
        com1.setSurvey(survey);
        com1.setReleasedDate(System.currentTimeMillis());
        com1.setUser(u2);
        com1.setText("yor1");
        com1.setNumberOfViolations(6);
        com1.setNumberOfLikes(8);
        com1 = commentRepository.save(com1);
        Comment com2 = new Comment();
        com2.setNumberOfLikes(9);
        com2.setSurvey(survey);
        com2.setUser(user1);
        LocalDateTime localDateTime = LocalDateTime.of(2024, 9, 5, 10, 30);
        // 2. Adım: LocalDateTime'ı ZonedDateTime'a dönüştür
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        // 3. Adım: ZonedDateTime'ı Instant'a dönüştür
        Instant instant = zonedDateTime.toInstant();
        // 4. Adım: Instant'tan milisaniye cinsinden zamanı al
        long millis = instant.toEpochMilli();
        com2.setReleasedDate(millis);
        com2.setNumberOfViolations(9);
        com2.setText("commentyazısı");
        com2 = commentRepository.save(com2);
    }

    @Override
    public List<Survey> getSurveysByHashtagId(Long hashtagId) {
        // TODO: sql method query olarak çekmeyi yap. Performans.
        List<Survey> surveys = surveyRepository.findAll();
        List<Survey> resultSurveys = new ArrayList<>();
        for (int i = 0; i < surveys.size(); i++) {
            List<Hashtag> hashtags = surveys.get(i).getHashtags();
            for (Hashtag hashtag : hashtags) {
                if (hashtag.getId().equals(hashtagId)) {
                    resultSurveys.add(surveys.get(i));
                }
            }
        }


        return resultSurveys;
    }

    @Override
    public Survey getSurveyById(Long id) {
        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        if (surveyOptional.isPresent()) {
            return surveyOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Survey> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        return surveys;
    }

    @Override
    public Survey create(Survey survey) throws HashtagNotProvidedException, NotEnoughChoicesException, NoCommentShouldBeProvidedException {
        // choice leri bul ve chooiceservive ile kaydet. survey e set et.
        if (survey.getHashtags().size() == 0) {
            throw new HashtagNotProvidedException("Hashtag not there for survey.");
        }
        if (survey.getChoices().size() < 2) {
            throw new NotEnoughChoicesException("Enough choices not provided for survey");
        }
        if (survey.getComments().size() > 0) {
            throw new NoCommentShouldBeProvidedException("Comment shouldn't be provided with the survey.");
        }
        survey.setComments(new ArrayList<>());
        List<Choice> toBeSavedChoices = survey.getChoices();
        survey.setChoices(new ArrayList<>());
        survey = surveyRepository.save(survey);
        List<Choice> listOfChoices = new ArrayList<>();
        for (int i = 0; i < toBeSavedChoices.size(); i++) {
            Choice currentChoice = toBeSavedChoices.get(i);
            currentChoice.setSurvey(survey);
            currentChoice = choiceRepository.save(currentChoice);
            listOfChoices.add(currentChoice);
        }
        survey.setChoices(listOfChoices);
        survey = surveyRepository.save(survey);


        /*
        // hashtags


        Survey s = surveyRepository.save(survey);
        */
        return survey;
    }
}
