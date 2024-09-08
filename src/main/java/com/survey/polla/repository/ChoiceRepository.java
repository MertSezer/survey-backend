package com.survey.polla.repository;

import com.survey.polla.model.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
