package com.survey.polla.controller;

import com.survey.polla.converter.CommentConverter;
import com.survey.polla.model.dto.CommentDto;
import com.survey.polla.model.dto.CommentUpdatableDto;
import com.survey.polla.model.entity.Comment;
import com.survey.polla.model.entity.Survey;
import com.survey.polla.model.exception.CommentAlreadyExistsException;
import com.survey.polla.model.exception.CommentNotFoundException;
import com.survey.polla.model.exception.DatabaseException;
import com.survey.polla.model.exception.SurveyNotFoundException;
import com.survey.polla.service.CommentService;
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

@Tag(name = "Comment API", description = "Comment related API located here.")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private CommentConverter commentConverter;

    @Operation(summary = "Form a new comment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is created successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Comment with this id already exists.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment for this survey is already created.", content = @Content)})
    @PostMapping("/")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto resultCommentDto;
        try {
            Comment comment = commentConverter.toEntity(commentDto);
            comment = commentService.createComment(comment, commentDto.getSurveyId());
            resultCommentDto = commentConverter.toDto(comment);
        } catch (SurveyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (CommentAlreadyExistsException cae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultCommentDto, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<CommentUpdatableDto> updateComment(@RequestBody CommentUpdatableDto commentDto) {
        Comment comment = commentConverter.toEntity(commentDto);
        try {
            commentService.updateComment(comment);
            return new ResponseEntity<>(commentDto, HttpStatus.OK);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<List<CommentDto>> getCommentsBySurveyId(@PathVariable(name = "surveyId") long surveyId) throws Exception {
        List<CommentDto> commentDtos = new ArrayList<>();
        Survey survey = surveyService.getSurveyById(surveyId);
        if (survey == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comment> comments = survey.getComments();
        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            commentDtos.add(commentConverter.toDto(comment));
        }
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<Comment> comments;
        List<CommentDto> commentDtos = new ArrayList<>();
        try {
            comments = commentService.getAllComments();
            for (int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                CommentDto commentDto = commentConverter.toDto(comment);
                commentDtos.add(commentDto);
            }
        } catch (DatabaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteCommentByCommentId(@PathVariable(name = "commentId") long commentId) throws Exception {
        try {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (DatabaseException e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
