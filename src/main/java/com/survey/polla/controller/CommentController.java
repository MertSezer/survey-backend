package com.survey.polla.controller;

import com.survey.polla.model.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @PostMapping("/")
    public ResponseEntity<Boolean> createComment(@RequestBody CommentDto commentDto)
    {
        // TODO: commentService.createComment();
        return  null;
    }
}
