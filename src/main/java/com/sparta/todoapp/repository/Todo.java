/*
할 일을 나타내는 엔티티 클래스
데이터베이스의 Todo 테이블과 매핑되며, 할 일의 제목, 내용, 작성자 이름, 비밀번호, 생성일 등의 정보를 저장
 */

package com.sparta.todoapp.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // Lombok 어노테이션으로, 모든 필드에 대한 getter 메서드를 자동으로 생성
@Entity // JPA 어노테이션으로, 이 클래스가 데이터베이스의 테이블과 매핑되는 엔티티임을 나타냄
@NoArgsConstructor // Lombok 어노테이션으로, 매개변수가 없는 기본 생성자를 자동으로 생성
public class Todo {

	@Id // JPA 어노테이션으로, 이 필드가 기본 키임을 나타냄
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // 기본 키 생성을 위한 전략을 시퀀스로 지정
	@Column(name = "todo_id", nullable = false) // 데이터베이스 컬럼 이름과 속성을 설정
	private Long todoId; // 할 일의 고유 ID

	private String title; // 할 일의 제목

	private String content; // 할 일의 내용

	private String userName; // 사용자의 이름

	private String password; // 할 일의 비밀번호

	private LocalDateTime createdAt; // 할 일이 생성된 날짜 및 시간

	@Builder // Lombok 어노테이션으로, 빌더 패턴을 사용하여 객체를 생성할 수 있도록 함
	public Todo(String title, String content, String userName, String password) {
		this.title = title; // 할 일의 제목 설정
		this.content = content; // 할 일의 내용 설정
		this.userName = userName; // 사용자의 이름 설정
		this.password = password; // 비밀번호 설정
		this.createdAt = LocalDateTime.now(); // 생성 시점의 날짜 및 시간 설정
	}

	// 할 일의 제목을 수정하는 메서드
	public void setTitle(String title) {
		this.title = title;
	}

	// 할 일의 내용을 수정하는 메서드
	public void setContent(String content) {
		this.content = content;
	}

	// 사용자의 이름을 수정하는 메서드
	public void setUserName(String userName) {
		this.userName = userName;
	}
}