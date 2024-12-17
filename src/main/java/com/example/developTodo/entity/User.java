package com.example.developTodo.entity;

import jakarta.persistence.*;
import lombok.Getter;

// 유저명, 이메일, 작성일, 비밀번호 필드 ( 작성일은 JPA Auditing )
@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // unique = true 조건을 설정하면 동명이인 안됨
    private String username;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false) // 필수 제약 조건을 설정하지 않을 거라면 column 애너테이션을 생략해도 자동으로 테이블에 컬럼이 생성된다. TODO 이메일 중복 불가 조건 추가하기
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
