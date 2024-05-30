/*
클라이언트와의 HTTP 요청을 처리하여 비즈니스 로직을 실행하고,
그 결과를 클라이언트에게 응답하는 중개자 역할을 수행
 */


package com.sparta.todoapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sparta.todoapp.dto.TodoRequestDTO;
import com.sparta.todoapp.dto.TodoResponseDTO;
import com.sparta.todoapp.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.todoapp.CommonResponse;
import com.sparta.todoapp.service.TodoService;
import lombok.AllArgsConstructor;

@RequestMapping("/v1.0/todo") // API 엔드포인트 기본 경로 설정
@RestController // 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냄
@AllArgsConstructor // Lombok 어노테이션으로, 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
public class TodoController {

    public final TodoService todoService; // TodoService 객체를 주입받아 사용


    // 각 메서드는 'CommonResponse' 객체를 반환하여 응답 메시지와 상태 코드를 포함.
    // 'ResponseEntity'를 사용하여 HTTP 상태 코드와 함께 응답을 반환.


    @PostMapping // HTTP POST 요청을 처리하여 새로운 할 일을 생성하는 엔드포인트
    public ResponseEntity<CommonResponse<TodoResponseDTO>> postTodo(@RequestBody TodoRequestDTO dto) {
        try {
            // 선택한 일정의 ID를 입력 받지 않은 경우 예외 처리
            if (dto.getTodoId() == null) {
                return ResponseEntity.badRequest()
                        .body(CommonResponse.<TodoResponseDTO>builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .msg("일정 ID를 입력해주세요.")
                                .build());
            }

            // 댓글 내용이 비어 있는 경우 예외 처리
            if (dto.getContent() == null || dto.getContent().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(CommonResponse.<TodoResponseDTO>builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .msg("댓글 내용을 입력해주세요.")
                                .build());
            }

            Todo todo = todoService.createTodo(dto); // Todo 생성

            // 일정이 DB에 저장되지 않은 경우 예외 처리
            if (todo == null) {
                return ResponseEntity.internalServerError()
                        .body(CommonResponse.<TodoResponseDTO>builder()
                                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .msg("일정을 생성하는 데 문제가 발생했습니다.")
                                .build());
            }

            TodoResponseDTO response = new TodoResponseDTO(todo); // 생성된 Todo를 DTO로 변환
            return ResponseEntity.ok()
                    .body(CommonResponse.<TodoResponseDTO>builder()
                            .statusCode(HttpStatus.OK.value())
                            .msg("생성이 완료 되었습니다.")
                            .data(response)
                            .build());
        } catch (Exception e) {
            // 예상치 못한 상황에서의 예외 처리
            return ResponseEntity.internalServerError()
                    .body(CommonResponse.<TodoResponseDTO>builder()
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .msg("일정 생성 중 예상치 못한 오류가 발생했습니다.")
                            .build());
        }
    }

    @GetMapping("/{todoId}") // 특정 ID의 할 일을 조회하는 엔드포인트
    public ResponseEntity<CommonResponse<TodoResponseDTO>> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId); // ID로 Todo 조회
        TodoResponseDTO response = new TodoResponseDTO(todo); // 조회된 Todo를 DTO로 변환
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("단건 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping // 모든 할 일을 조회하는 엔드포인트
    public ResponseEntity<CommonResponse<List<TodoResponseDTO>>> getTodos() {
        List<Todo> todos = todoService.getTodos(); // 모든 Todo 조회
        List<TodoResponseDTO> response = todos.stream()
                .map(TodoResponseDTO::new)
                .collect(Collectors.toList()); // Todo 목록을 DTO 목록으로 변환
        return ResponseEntity.ok()
                .body(CommonResponse.<List<TodoResponseDTO>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("목록 조회이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @PutMapping("/{todoId}") // 특정 ID의 할 일을 수정하는 엔드포인트
    public ResponseEntity<CommonResponse<TodoResponseDTO>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.updateTodo(todoId, dto); // ID로 Todo 수정
        TodoResponseDTO response = new TodoResponseDTO(todo); // 수정된 Todo를 DTO로 변환
        return ResponseEntity.ok()
                .body(CommonResponse.<TodoResponseDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("수정이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @DeleteMapping("/{todoId}") // 특정 ID의 할 일을 삭제하는 엔드포인트
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        todoService.deleteTodo(todoId, dto.getPassword()); // ID와 비밀번호로 Todo 삭제
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료 되었습니다.")
                .build());
    }


}