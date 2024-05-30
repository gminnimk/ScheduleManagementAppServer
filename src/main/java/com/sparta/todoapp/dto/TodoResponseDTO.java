/*
클라이언트에게 응답할 데이터를 나타내는 DTO 클래스.
서버에서 가져온 할 일의 정보를 클라이언트에게 전달하기 위해 사용.
*/

package com.sparta.todoapp.dto;

import java.time.LocalDateTime;

import com.sparta.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter // Lombok 어노테이션으로, 모든 필드에 대한 getter 메서드를 자동으로 생성
@Setter // Lombok 어노테이션으로, 모든 필드에 대한 setter 메서드를 자동으로 생성
public class TodoResponseDTO {

	private Long todoId; // 할 일의 ID

	private String title; // 할 일의 제목

	private String content; // 할 일의 내용

	private String userName; // 사용자의 이름

	private LocalDateTime createdAt; // 할 일이 생성된 날짜 및 시간

	// Todo 엔티티 객체를 받아서 TodoResponseDTO 객체를 생성하는 생성자
	public TodoResponseDTO(Todo todo) {
		this.todoId = todo.getTodoId(); // 할 일의 ID 설정
		this.title = todo.getTitle(); // 할 일의 제목 설정
		this.content = todo.getContent(); // 할 일의 내용 설정
		this.userName = todo.getUserName(); // 사용자의 이름 설정
		this.createdAt = todo.getCreatedAt().toLocalDateTime(); // 할 일이 생성된 날짜 및 시간 설정
	}
}