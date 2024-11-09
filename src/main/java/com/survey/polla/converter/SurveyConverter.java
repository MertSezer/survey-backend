package com.survey.polla.converter;

import com.survey.polla.model.dto.*;
import com.survey.polla.model.entity.*;
import com.survey.polla.service.HashtagService;
import com.survey.polla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component // Service, Configuration
public class SurveyConverter {
    @Autowired
    private UserService userService;
    @Autowired
    private HashtagService hashtagService;
    @Autowired
    private HashtagConverter hashtagConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private ChoiceConverter choiceConverter;

    public Survey toEntity(SurveyDto source) {
        Survey converted = new Survey();
        converted.setLikeCount(source.getLikeCount());
        converted.setBeginningDate(source.getBeginningDate());
        converted.setEndingDate(source.getEndingDate());
        converted.setTitle(source.getTitle());
        converted.setId(source.getId());
        converted.setLikeCount(source.getLikeCount());
        converted.setDescription(source.getDescription());
        converted.setPictureURL(source.getPictureURL());
        Long userId = source.getPublisherUserId();
        try {
            User publishedUser = userService.getUserById(userId);
            converted.setPublisherUser(publishedUser);
        } catch (Exception ex) {
            System.err.println("Published user is not found on DB. Searched user id: " + userId);
        }


        List<Hashtag> hashtags = new ArrayList<>();
        for (int i = 0; i < source.getHashtagIds().size(); i++) {
            Long hashtagId = source.getHashtagIds().get(i);
            hashtags.add(hashtagService.getHashtagById(hashtagId));
        }
        converted.setHashtags(hashtags);

        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < source.getComments().size(); i++) {
            CommentDto commentDto = source.getComments().get(i);
            Comment comment = commentConverter.toEntity(commentDto);
            comments.add(comment);
        }
        converted.setComments(comments);
        List<Choice> choices = new ArrayList<Choice>();
        for (int i = 0; i < source.getChoices().size(); i++) {
            String choiceText = source.getChoices().get(i);
            SurveyBasicDto surveyBasicDto = new SurveyBasicDto();
            surveyBasicDto.setId(source.getId());
            surveyBasicDto.setLikeCount(source.getLikeCount());
            surveyBasicDto.setTitle(source.getTitle());
            surveyBasicDto.setBeginningDate(source.getBeginningDate());
            ChoiceDto choiceDto = new ChoiceDto(null, 0D, choiceText, 0, surveyBasicDto);
            Choice choice = choiceConverter.toEntity(choiceDto);
            choices.add(choice);
        }
        converted.setChoices(choices);
        return converted;

    }

    public SurveyDto toDto(Survey source) {
        SurveyDto converted = new SurveyDto();
        converted.setLikeCount(source.getLikeCount());
        converted.setBeginningDate(source.getBeginningDate());
        converted.setEndingDate(source.getEndingDate());
        converted.setTitle(source.getTitle());
        converted.setId(source.getId());
        converted.setLikeCount(source.getLikeCount());
        converted.setDescription(source.getDescription());
        converted.setPictureURL(source.getPictureURL());
        UserDto userDto = userConverter.toDto(source.getPublisherUser());
        converted.setPublisherUserId(userDto.getId());
        List<Long> hashtagIds = new ArrayList<>();
        for (int i = 0; i < source.getHashtags().size(); i++) {
            Hashtag hashtag = source.getHashtags().get(i);
            hashtagIds.add(hashtag.getId());
        }
        converted.setHashtagIds(hashtagIds);

        List<CommentDto> comments = new ArrayList<>();
        for (int i = 0; i < source.getComments().size(); i++) {
            comments.add(commentConverter.toDto(source.getComments().get(i)));
        }
        converted.setComments(comments);

        List<String> choiceDtos = new ArrayList<>();
        for (int i = 0; i < source.getChoices().size(); i++) {
            choiceDtos.add(source.getChoices().get(i).getText());
        }
        converted.setChoices(choiceDtos);
        return converted;
    }
}
