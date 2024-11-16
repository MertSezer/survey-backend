package com.survey.polla.service;

import com.survey.polla.converter.HashtagConverter;
import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.model.exception.HashtagAlreadyExistsException;
import com.survey.polla.model.exception.HashtagNotFoundException;
import com.survey.polla.model.exception.SurveyExistsforHashtagException;
import com.survey.polla.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;
    @Autowired
    private HashtagConverter hashtagConverter;

    @Override
    public List<Hashtag> getAll() {
        List<Hashtag> result = hashtagRepository.findAll();
        return result;
    }

    @Override
    public Hashtag saveHashtag(Hashtag hashtag) throws HashtagAlreadyExistsException {
        Optional<Hashtag> optionalHashtag = hashtagRepository.findById(hashtag.getId());
        if( ! optionalHashtag.isPresent())
        {
            hashtag = hashtagRepository.save(hashtag);
            return hashtag;
        }
        else{
            throw new HashtagAlreadyExistsException("Hashtag already exists.");
        }
    }

    @Override
    public Hashtag updateHashtag(Hashtag hashtag) throws HashtagNotFoundException {
        Optional<Hashtag> optionalHashtag = hashtagRepository.findById(hashtag.getId());
        if( optionalHashtag.isPresent())
        {
            hashtag = hashtagRepository.save(hashtag);
            return hashtag;
        }
        else{
            throw new HashtagNotFoundException("Hashtag does not exist.");
        }
    }

    @Override
    public void removeHashtag(Long id) throws HashtagNotFoundException, SurveyExistsforHashtagException {
        Optional<Hashtag> optional = hashtagRepository.findById(id);
        // hashtage baglı anket varsa Exception at. 400 dönüşü yapabiliriz.
        if (optional.isPresent()) {
            if(!optional.get().getSurveys().isEmpty())
            {
                throw new SurveyExistsforHashtagException("Survey already exists for hashtag.");
            }
            hashtagRepository.delete(optional.get());
        } else {
            throw new HashtagNotFoundException("Hashtag couldn't be found.");
        }
    }

    @Override
    public Hashtag getHashtagById(Long id) {
        Optional<Hashtag> hashtagOptional = hashtagRepository.findById(id);
        if (hashtagOptional.isPresent())
        {
            Hashtag hashtag = hashtagOptional.get();
            return hashtag;
        }
        else
        {
            return null;
        }
    }
}


