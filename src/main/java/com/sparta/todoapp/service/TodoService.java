package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.TodoRequestDTO;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 할 일 생성
    public Todo createTodo(TodoRequestDTO dto) {
        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);
    }

    // 할 일 단건 조회
    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 할 일 전체 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }

    // 할 일 수정
    public Todo updateTodo(Long todoId, TodoRequestDTO dto) {
        Todo todo = checkPWAndGetTodo(todoId, dto.getPassword());

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(dto.getUserName());

        return todoRepository.save(todo);
    }

    private Todo checkPWAndGetTodo(Long todoId, String password) {
        Todo todo = getTodo(todoId);

        // 비밀번호 체크
        if (todo.getPassword() != null
                && !Objects.equals(todo.getPassword(), password)) {
            throw new IllegalArgumentException();
        }
        return todo;
    }

    public void deleteTodo(Long todoId, String password) {
        Todo todo = checkPWAndGetTodo(todoId, password);

        todoRepository.delete(todo);
    }
}