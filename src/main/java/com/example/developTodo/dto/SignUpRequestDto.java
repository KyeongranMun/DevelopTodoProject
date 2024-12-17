package com.example.developTodo.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto { // 회원가입 요청이 들어올 때 회원이름, 비밀번호, 이메일을 입력받음

    private final String username;

    private final String pw;

    private final String email;

    public SignUpRequestDto(String username, String pw, String email) {
        this.username = username;
        this.pw = pw;
        this.email = email;
    }
}
