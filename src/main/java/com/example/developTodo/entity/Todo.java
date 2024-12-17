package com.example.developTodo.entity;
// 작성 유저 고유 식별자, 할 일 제목, 할 일 내용, 작성일, 수정일 필드 (작성일 수정일은 JPA Auditing) -> 작성 유저명 필드 대신 유저 고유 식별자 필드를 가져야 함

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "todos")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo() {
    }

    public Todo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // 일정 수정 시 사용
    public void updateTodo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
