package com.survey.polla;

import com.survey.polla.model.*;
import com.survey.polla.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PollaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollaApplication.class, args);
	}

}

