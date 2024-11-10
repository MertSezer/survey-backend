package com.survey.polla.controller;


import com.survey.polla.converter.HashtagConverter;
import com.survey.polla.model.dto.HashtagDto;
import com.survey.polla.model.entity.Hashtag;
import com.survey.polla.model.exception.HashtagAlreadyExistsException;
import com.survey.polla.model.exception.HashtagNotFoundException;
import com.survey.polla.service.HashtagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // TODO: when removing a hashtag which is assigned to a survey is does not deleted.
    @Operation(summary = "Delete hashtag through parameter long hashtag id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hashtag is deleted successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "404", description = "Hashtag is not found.", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHashtag(@PathVariable("id") Long hashtagId) {
        try {
            hashtagService.removeHashtag(hashtagId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HashtagNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Getting all hashtags.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All hashtags are returned successfully. If there is no hashtags, it returns empty list.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))})})
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

    @Operation(summary = "Create a new hashtag.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hashtag is created successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HashtagDto.class))}),
            @ApiResponse(responseCode = "400", description = "Hashtag is not created because hashtag already exists.", content = @Content)})
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

    @Operation(summary = "Update the hashtag.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hashtag is updated successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HashtagDto.class))}),
            @ApiResponse(responseCode = "404", description = "Hashtag is not found.", content = @Content)})
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

