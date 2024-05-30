package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.CommentRequestDTO;
import com.sparta.todoapp.dto.CommentResponseDTO;
import com.sparta.todoapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 추가
    @PostMapping
    public ResponseEntity<CommentResponseDTO> addComment(@RequestBody CommentRequestDTO requestDTO) {
        return ResponseEntity.ok(new CommentResponseDTO(commentService.addComment(requestDTO.getTodoId(), requestDTO.getContent(), requestDTO.getUserId())));
    }
}