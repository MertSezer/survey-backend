package com.survey.polla.controller;

import com.survey.polla.converter.SurveyBasicConverter;
import com.survey.polla.converter.SurveyConverter;
import com.survey.polla.model.dto.SurveyBasicDto;
import com.survey.polla.model.dto.SurveyDto;
import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.exception.HashtagNotProvidedException;
import com.survey.polla.model.exception.NoCommentShouldBeProvidedException;
import com.survey.polla.model.exception.NotEnoughChoicesException;
import com.survey.polla.service.SurveyService;
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

@Tag(name = "Survey API", description = "Survey related API located here.")
@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyBasicConverter surveyBasicConverter;

    @Autowired
    private SurveyConverter surveyConverter;


    @Operation(summary = "Getting surveys by the parameter of hashtag id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting surveys by hashtag id is successfully. If there is no survey, it returns empty list.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))})})
    @GetMapping("/basic/{hashtagId}")
    public ResponseEntity<List<SurveyBasicDto>> getSurveysByHashtagId(@PathVariable Long hashtagId) {
        List<Survey> surveys = surveyService.getSurveysByHashtagId(hashtagId);
        List<SurveyBasicDto> resultdto = new ArrayList<SurveyBasicDto>();
        // Burada kaldım. List survey -> List surveybasicdto
        for (int i = 0; i < surveys.size(); i++) {
            /*
            Survey survey = surveys.get(i);
            SurveyBasicDto dto = new SurveyBasicDto(survey.getId(), survey.getTitle(), survey.getBeginningDate(), survey.getLikeCount());
            resultdto.add(dto);


             */
            Survey survey = surveys.get(i);
            SurveyBasicDto surveyBasicDto = surveyBasicConverter.toDto(survey);
            resultdto.add(surveyBasicDto);
        }
        //return ResponseEntity.ok(resultdto);
        ResponseEntity responseEntity = new ResponseEntity<>(resultdto, HttpStatus.OK);
        return responseEntity;
    }

    @Operation(summary = "Getting survey through parameter long survey id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting survey by survey id is successful.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SurveyDto.class))}),
            @ApiResponse(responseCode = "404", description = "Survey is not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SurveyDto.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<SurveyDto> getSurveyById(@PathVariable Long id) {
        Survey survey = surveyService.getSurveyById(id);
        if (survey == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SurveyDto surveyDto = surveyConverter.toDto(survey);
        ResponseEntity responseEntity = new ResponseEntity<>(surveyDto, HttpStatus.OK);
        return responseEntity;
    }

    @Operation(summary = "Getting all surveys.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting all surveys is successful. If there is no survey, it returns empty list.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))})})
    @GetMapping("/")
    public ResponseEntity<List<SurveyDto>> getSurveys() {
        List<SurveyDto> surveyDtos = new ArrayList<SurveyDto>();
        List<Survey> surveys = surveyService.getAllSurveys();
        for (int i = 0; i < surveys.size(); i++) {
            surveyDtos.add(surveyConverter.toDto(surveys.get(i)));
        }
        ResponseEntity responseEntity = new ResponseEntity<>(surveyDtos, HttpStatus.OK);
        return responseEntity;
    }

    @Operation(summary = "Create survey through parameter SurveyDto surveyDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Survey is created successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SurveyDto.class))}),
            @ApiResponse(responseCode = "404", description = "Hashtag is not provided at least 1. No comment should be given. At least 2 choices are provided. If these rules are not considered, HTTP status code will be 400.", content = @Content)})
    @PostMapping("/")
    public ResponseEntity<SurveyDto> createSurvey(@RequestBody SurveyDto surveyDto) {
        try {
            Survey survey = surveyConverter.toEntity(surveyDto);
            survey = surveyService.create(survey);
            SurveyDto surveyResultDto = surveyConverter.toDto(survey);
            return new ResponseEntity<>(surveyResultDto, HttpStatus.OK);
        } catch (HashtagNotProvidedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (NotEnoughChoicesException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoCommentShouldBeProvidedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // TODO: Future work: updateSurvey, deleteSurvey
}
