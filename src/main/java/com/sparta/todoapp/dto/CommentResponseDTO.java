package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Comment;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentResponseDTO {
    private Long commentId; // 댓글 ID
    private String content; // 댓글 내용
    private String userId; // 댓글 작성자 ID
    private Long todoId; // 일정 ID
    private Timestamp createdAt; // 댓글 작성 시간

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.todoId = comment.getTodo().getTodoId();
        this.createdAt = comment.getCreatedAt();
    }
}
