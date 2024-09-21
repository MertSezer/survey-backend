package com.survey.polla.converter;

import com.survey.polla.model.dto.ChoiceDto;
import com.survey.polla.model.entity.Choice;
import org.springframework.stereotype.Component;

@Component
public class ChoiceConverter {

    public Choice toEntity(ChoiceDto choiceDto) {
        Choice choice = new Choice();
        choice.setId(choiceDto.getId());
        choice.setPercentile(choiceDto.getPercentile());
        choice.setText(choiceDto.getText());
        //TODO: choice.setSurvey(choiceDto.getSurvey());
        choice.setNumberOfVotes(choiceDto.getNumberOfVotes());
        return choice;
    }

    public ChoiceDto toDto(Choice choice) {
        ChoiceDto choiceDto = new ChoiceDto();
        choiceDto.setId(choice.getId());
        choiceDto.setText(choice.getText());
        choiceDto.setPercentile(choice.getPercentile());
        //TODO: choiceDto.setSurvey(choiceDto.getSurvey());
        choiceDto.setNumberOfVotes(choice.getNumberOfVotes());
        return choiceDto;
    }
}