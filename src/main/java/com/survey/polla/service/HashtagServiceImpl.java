package com.survey.polla.service;

import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public List<Hashtag> getAll() {
        // 1. Repository inject edilmeli. (new)
        // 2. repository.findAll(); methodunu burada kullan ve dön.
        // // cntr + alt + V
        List<Hashtag> result = hashtagRepository.findAll();
        return result;
    }

    @Override
    public Hashtag saveHashtag(HashtagDto hashtagDto) {
        // hashtagDto (source, browser) -> hashtag (DB)
        Hashtag hashtag = new Hashtag();
        hashtag.setBeginDate(System.currentTimeMillis());
        hashtag.setDescription(hashtagDto.getDescription());
        hashtag.setText(hashtagDto.getText());
        hashtag = hashtagRepository.save(hashtag);
        return hashtag;
    }

    @Override
    public void removeHashtag(Long id) {
        Optional<Hashtag> optional = hashtagRepository.findById(id);
        if (optional.isPresent()) {
            hashtagRepository.delete(optional.get());
        } else {
            //TODO: hashtag repository'de bulunmayan hashtag silme
        }
    }
}


