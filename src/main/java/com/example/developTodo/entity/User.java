package com.example.developTodo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;

    public User() {
    }

    public User(String username, String pw, String email) {
        this.username = username;
        this.pw = pw;
        this.email = email;
    }

    // 회원 정보 수정 - 사용자명, 이메일
    public void updateUserInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
