package com.survey.polla.controller;


import com.survey.polla.converter.HashtagConverter;
import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.model.exception.HashtagAlreadyExistsException;
import com.survey.polla.model.exception.HashtagNotFoundException;
import com.survey.polla.service.HashtagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Hashtag API", description = "Hashtag related API located here.")
@RestController
@RequestMapping("/api/v1/hashtag")
public class HashtagController {
    // 1) service inject et.
    @Autowired
    private HashtagService hashtagService;
    @Autowired
    private HashtagConverter hashtagConverter;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHashtag(@PathVariable("id") Long hashtagId){
        try {
            hashtagService.removeHashtag(hashtagId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (HashtagNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<HashtagDto>> getAll() {
        // 2) service.getAll();
        List<Hashtag> hashtagEntityList = hashtagService.getAll();
        List<HashtagDto> hashtagDtoList = new ArrayList<>();
        for (Hashtag entity : hashtagEntityList) {
            hashtagDtoList.add(hashtagConverter.toDto(entity));
        }
        // Sonraki ders: HashtagDto Data Transfer Object.
        //List<User> users = userService.getAll();
        // return ResponseEntity.ok(result);
        return new ResponseEntity<>(hashtagDtoList, HttpStatus.OK);
    }

    // hashtag yaratsın. Postmapping
    @PostMapping("/")
    ResponseEntity<HashtagDto> createHashtag(@RequestBody HashtagDto hashtagDto) {
        try {
            Hashtag hashtag = hashtagConverter.toEntity(hashtagDto);
            hashtagService.saveHashtag(hashtag);
            hashtagDto.setId(hashtag.getId());
            return new ResponseEntity<>(hashtagDto, HttpStatus.OK);
        } catch (HashtagAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/")
    ResponseEntity<HashtagDto> updateHashtag(@RequestBody HashtagDto hashtagDto) {
        try {
            Hashtag hashtag = hashtagConverter.toEntity(hashtagDto);
            hashtag = hashtagService.updateHashtag(hashtag);
            return new ResponseEntity<>(hashtagDto, HttpStatus.OK);
        } catch (HashtagNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

