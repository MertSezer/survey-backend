package com.survey.polla.service;

import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;

import java.util.List;

public interface HashtagService {
    List<Hashtag> getAll();
    Hashtag saveHashtag(HashtagDto hashtagDto);
    void removeHashtag(Long id);
}
