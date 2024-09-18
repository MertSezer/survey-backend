package com.survey.polla.controller;

import com.survey.polla.converter.SurveyBasicConverter;
import com.survey.polla.model.dto.SurveyBasicDto;
import com.survey.polla.model.dto.SurveyDto;
import com.survey.polla.model.dto.UserDto;
import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.entity.User;
import com.survey.polla.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyBasicConverter surveyBasicConverter;

    @GetMapping("/basic/{hashtagId}")
    public ResponseEntity<List<SurveyBasicDto>> getSurveysByHashtagId(@PathVariable Long hashtagId) {
        List<Survey> surveys = surveyService.getSurveysByHashtagId(hashtagId);
        List<SurveyBasicDto> resultdto = new ArrayList<SurveyBasicDto>();
        // Burada kaldım. List survey -> List surveybasicdto
        for (int i = 0; i < surveys.size(); i++)
        {
            /*
            Survey survey = surveys.get(i);
            SurveyBasicDto dto = new SurveyBasicDto(survey.getId(), survey.getTitle(), survey.getBeginningDate(), survey.getLikeCount());
            resultdto.add(dto);


             */
            Survey survey =surveys.get(i);
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
         // TODO: implement getSurveyById in surveyServiceImpl
         SurveyDto resultDto = new SurveyDto();
         resultDto.setId(survey.getId());
         resultDto.setBeginningDate(survey.getBeginningDate());
         resultDto.setEndingDate(resultDto.getEndingDate());
         resultDto.setDescription(survey.getDescription());
         resultDto.setTitle(survey.getTitle());
         resultDto.setLikeCount(survey.getLikeCount());
         resultDto.setPictureURL(survey.getPictureURL());
         User publisherUser = survey.getPublisherUser();
         UserDto publisherUserDto = new UserDto(publisherUser.getId(), publisherUser.getName(), publisherUser.getSurname(), publisherUser.getUserName());
         resultDto.setPublisherUser(publisherUserDto);
         // TODO: listeleri dönüştür
        // TODO: converter katmanını entegre et.
         //resultDto.setChoices(survey.getChoices());
        ResponseEntity responseEntity = new ResponseEntity<>(resultDto, HttpStatus.OK);
        return responseEntity;
    }

}
