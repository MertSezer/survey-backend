package com.survey.polla.controller;

import com.survey.polla.converter.CommentConverter;
import com.survey.polla.model.dto.CommentDto;
import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.expection.SurveyNotFoundException;
import com.survey.polla.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment API", description = "Comment related API located here.")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentConverter commentConverter;
    // ilgili survey'e (surveyId) yorum yarat. (C)
    // survey tıklandığında (surveyId) ona ait tüm yorumları getir. (R)
    // comment değiştir. commentId (U)
    // comment sil. (commentId) (D)

    @PostMapping("/")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto resultCommentDto;
        try {
            Comment comment = commentService.createComment(commentConverter.toEntity(commentDto), commentDto.getSurveyId());
            resultCommentDto = commentConverter.toDto(comment);
        } catch (SurveyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultCommentDto, HttpStatus.OK);
    }
}
