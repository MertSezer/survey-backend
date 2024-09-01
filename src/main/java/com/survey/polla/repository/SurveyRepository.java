package com.survey.polla.repository;

import com.survey.polla.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long>{

}
