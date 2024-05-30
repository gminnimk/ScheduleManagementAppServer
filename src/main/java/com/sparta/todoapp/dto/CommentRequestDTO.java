package com.sparta.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Long todoId; // 일정 ID
    private String content; // 댓글 내용
    private String userId; // 댓글 작성자 ID
}
