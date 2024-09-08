package com.survey.polla.controller;

import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hashtag")
public class HashtagController {
    // 1) service inject et.
    @Autowired
    public HashtagService hashtagService;

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
        return new  ResponseEntity<>(hashtagDtoList, HttpStatus.OK);

    }
}

