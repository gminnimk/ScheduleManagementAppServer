package com.sparta.todoapp.model;


/*
댓글을 나타내는 Comment 클래스
 */


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;



    @ManyToOne(fetch = FetchType.LAZY) // Comment 클래스와 Todo 클래스가 다대일 관계임을 표시.
    @JoinColumn(name = "todo_id") // 댓글 테이블의 외래 키를 나타냄. , name 속성은 외래 키의 이름을 지정 (= "todo_id")
    private Todo todo;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // 생성자
    public Comment(String content, String userId, Todo todo) {
        this.content = content;
        this.userId = userId;
        this.todo = todo;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
