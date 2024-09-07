package com.survey.polla.repository;

import com.survey.polla.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
