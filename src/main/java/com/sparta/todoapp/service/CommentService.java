package com.sparta.todoapp.service;

import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    public Comment addComment(Long todoId, String content, String userId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            Comment comment = new Comment(content, userId, todo);
            return commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }
    }
}