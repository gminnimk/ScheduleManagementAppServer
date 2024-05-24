/*
클라이언트에게 일반적인 응답을 제공하는 클래스.
HTTP 상태 코드, 메시지, 그리고 데이터를 포함하여 클라이언트에게 응답.
 */

package com.sparta.todoapp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// Lombok 어노테이션으로, 클래스의 모든 필드에 대한 getter 메서드와 setter 메서드를 자동으로 생성합니다.
@Getter
@Setter
// Lombok 어노테이션으로, 빌더 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
@Builder
public class CommonResponse<T> {

	// HTTP 상태 코드를 저장하는 필드 (예: 200, 404 등)
	private Integer statusCode;

	// 응답 메시지를 저장하는 필드
	private String msg;

	// 실제 응답 데이터를 저장하는 제네릭 타입의 필드
	private T data;
}