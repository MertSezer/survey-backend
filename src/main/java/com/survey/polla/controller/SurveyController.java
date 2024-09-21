package com.survey.polla.controller;

import com.survey.polla.converter.SurveyBasicConverter;
import com.survey.polla.converter.SurveyConverter;
import com.survey.polla.model.dto.SurveyBasicDto;
import com.survey.polla.model.dto.SurveyDto;
import com.survey.polla.model.entity.Survey;
import com.survey.polla.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyBasicConverter surveyBasicConverter;

    @Autowired
    private SurveyConverter surveyConverter;

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

    @GetMapping("/{id}")
    public ResponseEntity<SurveyDto> getSurveyById(@PathVariable Long id) {
        Survey survey = surveyService.getSurveyById(id);
        SurveyDto surveyDto = surveyConverter.toDto(survey);
        ResponseEntity responseEntity = new ResponseEntity<>(surveyDto, HttpStatus.OK);
        return responseEntity;
    }

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

    @PostMapping("/")
    public ResponseEntity<SurveyDto> createSurvey(@RequestBody SurveyDto surveyDto) {
        Survey survey = surveyConverter.toEntity(surveyDto);
        survey = surveyService.create(survey);
        SurveyDto surveyResultDto = surveyConverter.toDto(survey);
        // TODO: 1) En az bir hashtag olmalı, eger hiç hashtag verilmezse HashtagNotProvidedException fırlat.
        // TODO: 2) En az iki adet choise içermeli, içermeze ChoiseSizeNotSuffientException.
        // TODO: 3) hiç comment verilmemelidir, eğer frontend den comment gelirse ya ignore edilmeli ya da exception.
        ResponseEntity responseEntity = new ResponseEntity<>(surveyResultDto, HttpStatus.OK);
        return responseEntity;
    }
}
