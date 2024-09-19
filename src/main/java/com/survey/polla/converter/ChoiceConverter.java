package com.survey.polla.converter;

import com.survey.polla.model.dto.ChoiceDto;
import com.survey.polla.model.entity.Choice;
import org.springframework.stereotype.Component;

@Component
public class ChoiceConverter {

    public Choice toEntity(ChoiceDto choiceDto) {
        Choice choice = new Choice();
        choice.setId(choice.getId());
        choice.setPercentile(choice.getPercentile());
        choice.setText(choice.getText());
        //TODO: choice.setSurvey(choiceDto.getSurvey());
        choice.setNumberOfVotes(choice.getNumberOfVotes());
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