package com.sparta.todoapp.model;


/*
댓글을 관리 & 연관 관계를 설정
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;


    // @OneToMany 어노테이션은 Todo 클래스와 Comment 클래스가 일대다 관계임을 표시!
    // => 하나의 일정에 여러 개의 댓글이 속할 수 있음.
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<Comment> comments; // 각 일정에 속하는 댓글을 관리.

    // 생성자
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
