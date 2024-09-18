package com.survey.polla.converter;

import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import org.springframework.stereotype.Component;

@Component // Service, Configuration
public class HashtagConverter {

    public Hashtag toEntity(HashtagDto source) {
        Hashtag target = new Hashtag();
        target.setId(source.getId());
        target.setText(source.getText());
        target.setDescription(source.getDescription());
        return target;
    }

    public HashtagDto toDto(Hashtag source) {
        HashtagDto target = new HashtagDto();
        target.setId(source.getId());
        target.setText(source.getText());
        target.setDescription(source.getDescription());
        return target;
    }
}
