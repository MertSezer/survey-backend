package com.survey.polla.converter;

import com.survey.polla.model.dto.CommentDto;
import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.dto.SurveyDto;
import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.model.entity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component // Service, Configuration
public class SurveyConverter {

    @Autowired
    private HashtagConverter hashtagConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private CommentConverter commentConverter;

    public Survey toEntity(SurveyDto source){
        Survey converted = new Survey();
        converted.setLikeCount(source.getLikeCount());
        converted.setBeginningDate(source.getBeginningDate());
        converted.setEndingDate(source.getEndingDate());
        converted.setTitle(source.getTitle());
        converted.setId(source.getId());
        converted.setLikeCount(source.getLikeCount());
        //TODO: converted.setChoices(source.getChoices());
        converted.setDescription(source.getDescription());
        converted.setPictureURL(source.getPictureURL());
        converted.setPublisherUser(userConverter.toEntity(source.getPublisherUser()));

        List<Hashtag> hashtags = new ArrayList<>();
        for (int i = 0; i<source.getHashtags().size(); i++){
            HashtagDto hashtagDto = source.getHashtags().get(i);
            Hashtag hashtag = hashtagConverter.toEntity(hashtagDto);
            hashtags.add(hashtag);
        }
        converted.setHashtags(hashtags);

        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < source.getComments().size(); i++)
        {
            CommentDto commentDto = source.getComments().get(i);
            Comment comment = commentConverter.toEntity(commentDto);
            comments.add(comment);
        }
        converted.setComments(comments);
        return converted;
    }

    public SurveyDto toDto(Survey source){
        SurveyDto converted = new SurveyDto();
        converted.setLikeCount(source.getLikeCount());
        converted.setBeginningDate(source.getBeginningDate());
        converted.setEndingDate(source.getEndingDate());
        converted.setTitle(source.getTitle());
        converted.setId(source.getId());
        converted.setLikeCount(source.getLikeCount());
        //converted.setChoices(source.getChoices());
        converted.setDescription(source.getDescription());
        converted.setPictureURL(source.getPictureURL());
        converted.setPublisherUser(userConverter.toDto(source.getPublisherUser()));
        List<HashtagDto> hashtags = new ArrayList<>();
        for(int i = 0; i<source.getHashtags().size(); i++){
           Hashtag hashtag = source.getHashtags().get(i);
            hashtags.add(hashtagConverter.toDto(hashtag));
        }
        converted.setHashtags(hashtags);

        List<CommentDto> comments = new ArrayList<>();
        for (int i = 0; i < source.getComments().size(); i++)
        {
            comments.add(commentConverter.toDto(source.getComments().get(i)));
        }
        converted.setComments(comments);
        return converted;

    }
}
