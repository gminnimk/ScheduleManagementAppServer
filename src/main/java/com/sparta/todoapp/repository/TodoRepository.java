/*
Todo 엔티티를 관리하기 위한 JpaRepository 인터페이스. 기본적인 CRUD(Create, Read, Update, Delete) 기능을 제공하며, 데이터베이스와의 상호작용을 처리.
 */


package com.sparta.todoapp.repository;

import com.sparta.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TodoRepository 인터페이스는 JPA를 사용하여 Todo 엔티티를 관리하기 위한 리포지토리
// JpaRepository를 상속받아 기본적인 CRUD (생성, 읽기, 업데이트, 삭제) 기능을 자동으로 제공
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // JpaRepository<Todo, Long>는 Todo 엔티티와 그 기본 키 타입(Long)을 제네릭 타입으로 받음
    // 별도의 메서드 선언 없이도 다양한 데이터베이스 작업을 수행
}