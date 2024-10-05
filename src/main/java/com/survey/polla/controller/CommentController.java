package com.survey.polla.controller;

import com.survey.polla.model.dto.CommentDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comment API", description = "Comment related API located here.")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @PostMapping("/")
    public ResponseEntity<Boolean> createComment(@RequestBody CommentDto commentDto) {
        // TODO: commentService.createComment();
        return null;
    }
}
