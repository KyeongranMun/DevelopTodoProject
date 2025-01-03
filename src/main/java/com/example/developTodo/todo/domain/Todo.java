package com.example.developTodo.todo.domain;

import com.example.developTodo.common.domain.BaseEntity;
import com.example.developTodo.user.domain.User;
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
    @ManyToOne(fetch = FetchType.LAZY)
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
