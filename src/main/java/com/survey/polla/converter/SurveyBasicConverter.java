package com.survey.polla.converter;

import com.survey.polla.model.dto.SurveyBasicDto;
import com.survey.polla.model.entity.Survey;
import org.springframework.stereotype.Component;

@Component // Service, Configuration
public class SurveyBasicConverter {

    public Survey toEntity(SurveyBasicDto source){
        Survey target = new Survey();
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setBeginningDate(source.getBeginningDate());
        target.setLikeCount(source.getLikeCount());
        return target;
    }

    public SurveyBasicDto toDto(Survey source){
        SurveyBasicDto target = new SurveyBasicDto();
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setBeginningDate(source.getBeginningDate());
        target.setLikeCount(source.getLikeCount());
        return target;
    }
}
