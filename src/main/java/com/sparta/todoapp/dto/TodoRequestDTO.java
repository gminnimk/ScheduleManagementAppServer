package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter // Lombok 어노테이션으로, 모든 필드에 대한 getter 메서드를 자동으로 생성
@Setter // Lombok 어노테이션으로, 모든 필드에 대한 setter 메서드를 자동으로 생성
public class TodoRequestDTO {

    private Long todoId;

    private String title; // 할 일의 제목을 저장하는 필드

    private String content; // 할 일의 내용

    private String userName; // 사용자의 이름

    private String password; // 할 일의 비밀번호


    // DTO를 Todo 엔티티 객체로 변환하는 메서드
    public Todo toEntity() {  // 이 메서드는 TodoRequestDTO 객체를 Todo 엔티티 객체로 변환
        //필드 값을 Todo 엔티티의 빌더 패턴을 사용하여 설정한 후, Todo 객체를 생성하고 반환
        return Todo.builder()
                .title(title) // 제목 설정
                .content(content) // 내용 설정
                .userName(userName) // 사용자 이름 설정
                .password(password) // 비밀번호 설정
                .build(); // Todo 객체 생성 및 반환
    }

    // getter 메서드 추가
    public Long getTodoId() {
        return this.todoId;
    }

}