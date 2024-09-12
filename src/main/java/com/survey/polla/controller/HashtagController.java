package com.survey.polla.controller;

import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hashtag")
public class HashtagController {
    // 1) service inject et.
    @Autowired
    public HashtagService hashtagService;

    @DeleteMapping("/{id}")
    public void deleteHashtag(@PathVariable("id") Long hashtagId) {
        hashtagService.removeHashtag(hashtagId);
    }
    @GetMapping("/")
    public ResponseEntity<List<HashtagDto>> getAll() {


        // 2) service.getAll();
        List<Hashtag> hashtagEntityList = hashtagService.getAll();
        List<HashtagDto> hashtagDtoList = new ArrayList<>();
        for (Hashtag entity: hashtagEntityList){
            HashtagDto hashtagDto = new HashtagDto(entity.getId(), entity.getText(), entity.getDescription());
            hashtagDtoList.add(hashtagDto);
        }
        // Sonraki ders: HashtagDto Data Transfer Object.
        //List<User> users = userService.getAll();
        // return ResponseEntity.ok(result);
        return new ResponseEntity<>(hashtagDtoList, HttpStatus.OK);
    }

    // hashtag yaratsın. Postmapping
    @PostMapping("/")
    ResponseEntity<HashtagDto> createHashtag(@RequestBody HashtagDto hashtagDto) {
        /*
        Hashtag savedHashtag = hashtagService.saveHashtag(hashtagDto);
        HashtagDto result = new HashtagDto(savedHashtag.getId(), savedHashtag.getText(), savedHashtag.getDescription());
        return ResponseEntity.ok(result);

         */
        // TODO: when creating hashtag, also create gündem, if gündem already exists in hashtag table
        // return 400 bad request
        Hashtag hashtag = hashtagService.saveHashtag(hashtagDto);
        hashtagDto.setId(hashtag.getId());
        return ResponseEntity.ok(hashtagDto);


    }
}

